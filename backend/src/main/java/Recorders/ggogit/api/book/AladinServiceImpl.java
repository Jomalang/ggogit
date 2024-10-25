package Recorders.ggogit.api.book;

import Recorders.ggogit.domain.book.entity.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AladinServiceImpl implements AladinService {

    @Autowired
    private AladinRepository aladinRepository;

    private final String ALADIN_ITEM_SEARCH_URL = "https://www.aladin.co.kr/ttb/api/" +
            "ItemSearch.aspx?ttbkey=ttbrhkddlr982013001&Query=%s&MaxResults=10&Cover=Big&Output=JS&Version=20131101";
            // 여기서 Query= 뒤에 들어간 %s 에 html 뷰의 input 이름의 keyword 값이 맵핑 됨, 검색 결과 값 MaxResults=100 으로 했는데 그보다 적게 저장되는 것 같음

    private final String ALADIN_ITEM_LOOKUP_URL = "https://www.aladin.co.kr/ttb/api/" +
            "ItemLookUp.aspx?ttbkey=ttbrhkddlr982013001&itemIdType=ISBN&ItemId=%s&Cover=Big&output=JS&Version=20131101";
            // 이 url 은 totalPage 저장에 사용함

    @Override
    @Transactional
    public void saveBooks(String keyword) {
        List<BookDto> books = fetchBooksFromAladin(keyword);
        saveBooksToDB(books);
    }

    private List<BookDto> fetchBooksFromAladin(String keyword) {
        String url = String.format(ALADIN_ITEM_SEARCH_URL, keyword); // 키워드를 API URL 에 삽입
        RestTemplate restTemplate = new RestTemplate(); //  HTTP 요청을 보내고 응답을 처리하는 데 사용
        String jsonResponse = restTemplate.getForObject(url, String.class); // 주어진 URL 로 GET 요청을 보내고, 응답을 json 이 문자열 이니깐 String 으로 반환

        System.out.println("JSON Response: " + jsonResponse);

        ObjectMapper mapper = new ObjectMapper(); // Jackson 라이브러리에서 JSON 데이터를 Java 객체로 변환하는 데 사용
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // JSON 데이터에 포함된 필드가 Java 객체의 클래스에 정의되지 않을 때 예외를 발생시키지 않게 함
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); // JSON 데이터에서 배열로 예상되는 필드에 단일 값이 있을 때 그 값을 배열로 처리할 수 있도록 함

        try {
            Map<String, Object> responseMap = mapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {}); // JSON 응답을 Map<String, Object>로 변환
            List<Map<String, Object>> itemList = (List<Map<String, Object>>) responseMap.get("item"); // item 키에 해당하는 리스트를 추출

            List<BookDto> books = new ArrayList<>();   // 각 아이템을 BookDto 로 변환하여 books 리스트에 추가
            for (Map<String, Object> item : itemList) {
                BookDto book = mapper.convertValue(item, BookDto.class);
                books.add(book);
            }

            return books; // 메핑이 완료된 bookDto 의 값을 아래의 코드에 넘김
        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // 에러 발생 시 빈 리스트 반환
        }
    }

    private void saveBooksToDB(List<BookDto> books) {  // 위에서 만든 books 을 매개변수로 사용하여 디비에 저장하는 코드
        for (BookDto bookDto : books) {
            try {
                String isbn = bookDto.getIsbn13();
                if (!aladinRepository.existsByIsbn(isbn)) { // @@@@@중복데이터 검사 (isbn 이 이미 존재하지 않으면)
                    Book book = convertToEntity(bookDto);  // BookDto 객체를 Book 엔티티 객체로 변환
                    aladinRepository.save(book);  // 알라딘 레포지로 넘겨 멥퍼가 작동하게 해서 데이터베이스에 저장
                    System.out.println("Saved book: " + book.getTitle());
                } else {
                    System.out.println("Book already exists: " + bookDto.getTitle());
                }
            } catch (Exception e) {
                System.err.println("Error processing book: " + bookDto.getTitle());
                e.printStackTrace();
            }
        }
    }

    private Book convertToEntity(BookDto dto) { // 위에서 이용하는 BookDto 객체를 Book 엔티티 객체로 변환 시 매핑해주는 코드
        Book book = new Book();
        book.setMemberId(999L); // 멤버아이디 임의 지정
        book.setBookCategoryId(1L); // 북카테고리값 임의 지정
        book.setTitle(dto.getTitle());
        String[] authorInfo = separateAuthorAndTranslator(dto.getAuthor()); // author 와 translator 분리
        book.setAuthor(authorInfo[0]);
        book.setTranslator(authorInfo[1]);
        book.setIsbn(dto.getIsbn13());  // 알라딘에서 isbn 보다 isbn13 를 사용하는 것을 권장하므로 수정하였습니다
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPubDate());
//        book.setTotalPage(getTotalPage(dto.getIsbn13())); // isbn13 으로 itemLookup url 를 이용하여 총페이지를 가져옴
        book.setImageFile(dto.getCover());
        book.setDeleted(false); // book entity 의 isDeleted
        book.setUpdateTime(LocalDateTime.now());
        book.setCreateTime(LocalDateTime.now());

        return book; // 여기서 변환된 book 을 위의 코드로 db 에 저장
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
        if (authors.length() > 0) {
            authors.setLength(authors.length() - 2); // 마지막 쉼표 제거
        }

        // 번역가가 없으면 null 반환
        String translatorResult = translators.length() > 0 ? translators.substring(0, translators.length() - 2) : null;

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

    private int getTotalPage(String isbn) {
        String url = String.format(ALADIN_ITEM_LOOKUP_URL, isbn); // 위 알라딘이 url 의 %s 에 isbn 값을 대입해 요청 url 을 생성
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(url, String.class); // json 이 문자열로 응답하기 때문에 이 형식으로 응답을 받는다

        ObjectMapper mapper = new ObjectMapper(); // 객체 mapper 를 사용해 json 을 이 코드에서 사용할  객체로 변환 준비
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 미리 정의된 속성을 제외한 속성에 대한 오류를 무시하도록 설정

        try {
            Map<String, Object> responseMap = mapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {}); // json 를 map(key:value) 형식으로 받는다
            List<Map<String, Object>> itemList = (List<Map<String, Object>>) responseMap.get("item"); // item 이라는 큰 단위 안에 책 데이터 정보가 있는데 그  item 의 리스트들을 불러옴

            if (itemList != null && !itemList.isEmpty()) { // itemList 가 null 이 아니고 비어 있지 않은지 확인
                Map<String, Object> item = itemList.get(0); // index 0 번째 즉 첫번째 item 을 가져옴
                Object subInfo = item.get("subInfo"); // 아이템의 subInfo를 가져옴
                if (subInfo instanceof Map) { // subInfo가 Map 타입인지 확인
                    Object itemPage = ((Map<String, Object>) subInfo).get("itemPage");  // itemPage = totalPage 를 가져옴
                    if (itemPage instanceof Integer) { // itemPage가 Integer 타입인지 확인
                        return (Integer) itemPage; // 총 페이지 수 반환
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ISBN " + isbn + "의 총 페이지 수를 가져오는 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        return 0; // 디비에서 totalPage 가 not null 이기 때문에 페이지 수를 가져오지 못한 경우 0 반환
    }
}