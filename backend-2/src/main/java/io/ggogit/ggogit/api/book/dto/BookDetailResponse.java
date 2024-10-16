package io.ggogit.ggogit.api.book.dto;

import io.ggogit.ggogit.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailResponse {

    private Long id;
    private String publishDate;
    private Integer totalPage;
    private Long bookCategoryId;
    private String author;
    private String isbn;
    private String publisher;
    private String title;
    private String imageFile;
    private String createTime;
    private String updateTime;

    public static BookDetailResponse of(Book book) {
        return BookDetailResponse.builder()
                .id(book.getId())
                .publishDate(book.getPublishDate().toString())
                .totalPage(book.getTotalPage())
                .bookCategoryId(book.getBookCategory().getId())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .title(book.getTitle())
                .imageFile(book.getImageFile())
                .createTime(book.getCreateTime().toString())
                .updateTime(book.getUpdateTime().toString())
                .build();
    }
}
