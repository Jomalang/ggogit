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

@Service
public class AladinServicrImpl implements AladinService {

    @Autowired
    AladinRepository aladinRepository;

    private final String ALADIN_API_URL = "https://www.aladin.co.kr/ttb/api/" +
            "ItemSearch.aspx?ttbkey=ttbrhkddlr982013001&Query=%s&MaxResults=100&Cover=Big&Output=JS&Version=20131101";

    @Override
    @Transactional
    public void saveBooks(String keyword) {
        List<BookDto> books = fetchBooksFromAladin(keyword);
        saveBooksToDB(books);
    }

    private List<BookDto> fetchBooksFromAladin(String keyword) {
        String url = String.format(ALADIN_API_URL, keyword); // 키워드를 API URL 에 삽입
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

    private void saveBooksToDB(List<BookDto> books) { // 위에서 만든 books 을 매개변수로 사용하여 디비에 저장하는 코드
        for (BookDto bookDto : books) {
            try {
                Book book = convertToEntity(bookDto); // BookDto 객체를 Book 엔티티 객체로 변환
                aladinRepository.save(book); // 알라딘 레포지로 넘겨 멥퍼가 작동하게 해서 데이터베이스에 저장
                System.out.println("Saved book: " + book.getTitle());
            } catch (Exception e) {
                System.err.println("Error saving book: " + bookDto.getTitle());
                e.printStackTrace();
            }
        }
    }

    private Book convertToEntity(BookDto dto) { // 위에서 이용하는 BookDto 객체를 Book 엔티티 객체로 변환 시 매핑해주는 코드
        Book book = new Book();
        book.setId(1L); // 아이디값 임의 지정
        book.setMemberId(14L); // 멤버아이디 임의 지정
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setTotalPage(100L); // 알라딘에서 책의 총 페이지 수 확인을 지원 안하므로 임의 지정 값 넣음
        book.setImageFile(dto.getCover());
        book.setResourceFrom(true); // 다 알라딘에서 받은 데이터이므로 true 값 고정
        book.setUpdateTime(LocalDateTime.now());
        book.setCreateTime(LocalDateTime.now());
        book.setIsbn(dto.getIsbn());
        book.setBookCategoryId(1L); // 북카테고리값 임의 지정

        return book; // 여기서 변환된 book 을 위의 코드로 db 에 저장
    }
}
