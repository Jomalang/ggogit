package Recorders.ggogit.domain.book.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDetailView {
    private String imageFileName;
    private String bookCategoryName;
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime publicDate;
    private Long totalPage;

    public Date getPublicDate() {
        return Date.from(publicDate.atZone(ZoneId.systemDefault()).toInstant());
    }
}