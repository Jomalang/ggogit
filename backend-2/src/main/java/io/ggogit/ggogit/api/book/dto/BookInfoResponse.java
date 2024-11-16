package io.ggogit.ggogit.api.book.dto;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class BookInfoResponse {
    Long id;
    String imageFile;
    String title;
    Integer totalPage;
    String translator;
    //TODO 차후 List<String>으로 진행할지, category 계층화 시킬것인지 고민해야함. 알라딘 카테고리는 계층화 되어있음.
    BookCategoryDto category;
    LocalDate publishDate;
    String isbn;
    String author;
    String publisher;
    LocalDateTime createTime;

    public static BookInfoResponse of(final Book book) {

        return BookInfoResponse.builder()
                .id(book.getId())
                .imageFile(book.getImageFile())
                .title(book.getTitle())
                .totalPage(book.getTotalPage())
                .category(BookCategoryDto.of(book.getBookCategory()))
                .publishDate(book.getPublishDate())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .translator(book.getTranslator())
                .publisher(book.getPublisher())
                .createTime(book.getCreateTime())
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class BookCategoryDto {
        private Long id;
        private String name;

        public static BookCategoryDto of(final BookCategory bookCategory) {
            return BookCategoryDto.builder()
                    .id(bookCategory.getId())
                    .name(bookCategory.getName())
                    .build();
        }
    }
}

