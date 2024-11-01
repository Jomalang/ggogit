package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.api.book.dto.BookCategoryResponse;
import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeCardResponse {
    private Long bookId;
    private BookCategoryDto bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private String bookPublishedYear;
    private Boolean bookComplete;
    private String coverImageName;
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String seedKorName;
    private String title;
    private Boolean visibility;
    private LocalDateTime leafCreatedAt;

    public static TreeCardResponse toEntity(Book book, boolean complateBook, Tree tree, Seed Seed, Long memberId){
        LocalDate publishYear = book.getPublishDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return TreeCardResponse.builder()
                .bookId(book.getId())
                .bookCategory(BookCategoryDto.of(book.getBookCategory()))
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor())
                .bookTranslator(book.getTranslator())
                .bookPublisher(book.getPublisher())
                .bookPublishedYear(publishYear.format(formatter))
                .bookComplete(complateBook)
                .coverImageName(book.getImageFile())
                .treeId(tree.getId())
                .memberId(memberId)
                .seedId(Seed.getId())
                .seedKorName(Seed.getKorName())
                .title(tree.getTitle())
                .visibility(tree.getVisibility())
                .leafCreatedAt(tree.getUpdateTime())
                .build();
    }

    public static TreeCardResponse toEntity(String coverImage, Member member, Tree tree, Seed seed, String nickname){
        return TreeCardResponse.builder()
                .coverImageName(coverImage)
                .treeId(tree.getId())
                .bookAuthor(nickname)
                .memberId(member.getId())
                .seedId(seed.getId())
                .seedKorName(seed.getKorName())
                .title(tree.getTitle())
                .visibility(tree.getVisibility())
                .leafCreatedAt(tree.getUpdateTime())
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
