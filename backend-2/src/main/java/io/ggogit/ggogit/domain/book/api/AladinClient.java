package io.ggogit.ggogit.domain.book.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.domain.book.api.dto.AladinLookUpResultDto;
import io.ggogit.ggogit.domain.book.api.dto.AladinSearchResultDto;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import io.ggogit.ggogit.type.AladinBookSearchType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class AladinClient {

    private final BookCategoryRepository bookCategoryRepository;
    @Value("${aladin.api.key}")
    private String apiKey;

    private final BookRepository bookRepository;

    private final String ALADIN_ITEM_SEARCH_URL = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx";

    private final String ALADIN_ITEM_LOOKUP_URL = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";

    public List<Book> fetchBooks(String keyword, String searchType) {

        String url = getAladinItemSearchUrl(keyword, searchType);
        RestTemplate restTemplate = new RestTemplate();

        // log.info("Aladin API를 통해 책 정보를 가져옵니다. keyword={}", keyword);
        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        AladinSearchResultDto searchResult;
        try {
            searchResult = mapper.readValue(jsonResponse, AladinSearchResultDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<Book> books = searchResult.getItem().stream()
                .map(Book::of)
                .toList();

        fetchBooksByIsbn(books); // ISBN으로 상세 정보 가져오기 (total page)

        return books;
    }

    private List<Book> fetchBooksByIsbn(List<Book> books) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for (Book book : books) {
            String url = getAladinItemLookupUrl(book.getIsbn());
            // log.info("API 호출", url);
            RestTemplate restTemplate = new RestTemplate();

            // log.info("Aladin API를 통해 책 상세 정보를 가져옵니다. isbn={}", book.getIsbn());
            String jsonResponse = restTemplate.getForObject(url, String.class);

            // 작가 데이터 처리
            String[] authorAndTranslator = separateAuthorAndTranslator(book.getAuthor());
            book.setAuthor(authorAndTranslator[0]);
            book.setTranslator(authorAndTranslator[1]);

            AladinLookUpResultDto searchResult;
            try {
                searchResult = mapper.readValue(jsonResponse, AladinLookUpResultDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            AladinLookUpResultDto lookUpResult = mapper.convertValue(searchResult, AladinLookUpResultDto.class);
            book.setTotalPage(lookUpResult.getItem().getFirst().getSubInfo().getItemPage());

            // 카테고리 호출
            Long categoryId = Long.valueOf(lookUpResult.getItem().getFirst().getCategoryId());
            int index = lookUpResult.getItem().getFirst().getCategoryName().split(">").length - 1;
            String categoryName = lookUpResult.getItem().getFirst().getCategoryName().split(">")[index];

            // 조회
            BookCategory category = bookCategoryRepository.findById(categoryId).orElseGet(() -> {
                BookCategory newCategory = BookCategory.of(categoryId, categoryName);
                return bookCategoryRepository.save(newCategory); // 없으면 저장
            });

            book.setBookCategory(category);
        }

        return books;
    }

    private String getAladinItemSearchUrl(String keyword, String searchType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ALADIN_ITEM_SEARCH_URL)
                .queryParam("ttbkey", apiKey)
                .queryParam("QueryType", AladinBookSearchType.of(searchType).getApiValue()) // 검색 조건(제목, 저자, 출판사)
                .queryParam("Query", keyword) // 도서 검색 결과
                .queryParam("MaxResults", 100) // 최대 수 100
                .queryParam("Cover", "Big")
                .queryParam("Output", "JS")
                .queryParam("Version", "20131101");
        return builder.build(false).toUriString();
    }

    private String getAladinItemLookupUrl(String isbn) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ALADIN_ITEM_LOOKUP_URL)
                .queryParam("ttbkey", apiKey)
                .queryParam("itemIdType", "ISBN")
                .queryParam("ItemId", isbn)
                .queryParam("Cover", "Big")
                .queryParam("output", "JS")
                .queryParam("Version", "20131101");
        return builder.build(false).toUriString();
    }

    private String[] separateAuthorAndTranslator(String authorString) {  // 저자 데이터에서 translator 분리
        StringBuilder authors = new StringBuilder(); // 데이터를 분리할 그릇 생성
        StringBuilder translators = new StringBuilder();

        Pattern pattern = Pattern.compile("(.*?)\\s*\\((지은이|저자|그림|저|옮긴이|감수|번역|엮은이|역|편역)\\)"); // 데이터 분리하는 정규식
        Matcher matcher = pattern.matcher(authorString); // 분리된 데이터에서 패턴을 찾기 위해 Matcher 객체 생성

        while (matcher.find()) { // 패턴에 맞는 모든 항목을 반복적으로 찾아서 처리
            String name = matcher.group(1).trim(); // 모든 이름 데이터를 처리하고
            String role = matcher.group(2); // 모든 역할 데이터를 처리한다

            if (role.matches("지은이|저자|그림|저")) { // 역할에 따라 저자와 번역가에 저장한다
                authors.append(name).append(" (").append(role).append("), ");
            } else if (role.matches("옮긴이|감수|번역|엮은이|역|편역")) {
                translators.append(name).append(" (").append(role).append("), ");
            }
        }

        // 마지막의 쉼표 및 공백 제거
        if (!authors.isEmpty()) {
            authors.setLength(authors.length() - 2); // 마지막 쉼표 제거
        }

        // 번역가가 없으면 null 반환
        String translatorResult = !translators.isEmpty() ? translators.substring(0, translators.length() - 2) : null;

        // 최종적으로 저자와 번역가 문자열에서 불필요한 쉼표 및 공백 제거
        String finalAuthors = authors.toString().replaceAll(",\\s*,", ", ").trim();
        String finalTranslators = translatorResult != null ? translatorResult.replaceAll(",\\s*,", ", ").trim() : null;

        // 문자열의 처음에 쉼표가 있는 경우 제거
        if (finalAuthors.startsWith(",")) {
            finalAuthors = finalAuthors.substring(1).trim();
        }
        if (finalTranslators != null && finalTranslators.startsWith(",")) {
            finalTranslators = finalTranslators.substring(1).trim();
        }

        return new String[]{finalAuthors, finalTranslators}; // 각각 문자열 형태로 반환한다
    }
}