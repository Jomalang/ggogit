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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    public List<Book> fetchBooks(String keyword) {

        String url = getAladinItemSearchUrl(keyword);
        RestTemplate restTemplate = new RestTemplate();

        log.info("Aladin API를 통해 책 정보를 가져옵니다. keyword={}", keyword);
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

        // TODO: 임시 도서 카테고리 등록
        BookCategory bookCategory = bookCategoryRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("책 카테고리가 존재하지 않습니다."));

        for (Book book : books) {
            String url = getAladinItemLookupUrl(book.getIsbn());
            RestTemplate restTemplate = new RestTemplate();

            log.info("Aladin API를 통해 책 상세 정보를 가져옵니다. isbn={}", book.getIsbn());
            String jsonResponse = restTemplate.getForObject(url, String.class);

            System.out.println(jsonResponse);
            AladinLookUpResultDto searchResult;
            try {
                searchResult = mapper.readValue(jsonResponse, AladinLookUpResultDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            AladinLookUpResultDto lookUpResult = mapper.convertValue(searchResult, AladinLookUpResultDto.class);
            book.setTotalPage(lookUpResult.getItem().getFirst().getSubInfo().getItemPage());
            book.setBookCategory(bookCategory);
        }

        return books;
    }

    private String getAladinItemSearchUrl(String keyword) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ALADIN_ITEM_SEARCH_URL)
                .queryParam("ttbkey", apiKey)
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
}