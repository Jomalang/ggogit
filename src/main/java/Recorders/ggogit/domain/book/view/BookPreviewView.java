package Recorders.ggogit.domain.book.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPreviewView {

    //도서 식별자
    Long id;
    String imageFile;
    String title;
    //TODO 차후 List<String>으로 진행할지, category 계층화 시킬것인지 고민해야함. 알라딘 카테고리는 계층화 되어있음.
    String category;
    LocalDate publishDate;
    String author;
    String publisher;
    LocalDateTime createTime;


}
