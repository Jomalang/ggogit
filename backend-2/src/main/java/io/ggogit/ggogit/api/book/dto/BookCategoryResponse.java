package io.ggogit.ggogit.api.book.dto;

import io.ggogit.ggogit.domain.book.entity.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryResponse {

    List<BookCategoryDto> bookCategories;

    public static BookCategoryResponse of(List<BookCategory> bookCategories) {
        return BookCategoryResponse.builder()
                .bookCategories(bookCategories.stream()
                        .map(BookCategoryDto::of)
                        .toList())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookCategoryDto {
        private Long id;
        private String name;

        public static BookCategoryDto of(BookCategory bookCategory) {
            return BookCategoryDto.builder()
                    .id(bookCategory.getId())
                    .name(bookCategory.getName())
                    .build();
        }
    }
}
