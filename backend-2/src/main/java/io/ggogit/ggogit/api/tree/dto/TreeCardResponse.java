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
    private int cardType = 0;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private String bookPublishedYear;
    private Boolean bookCompleted;
    private String cardImage;
    private String seedKorName;
    private Long leafCount;
    private String treeTitle;
    private Boolean visibility;
    private String updateTime;

    public static String changeUpdateTime(LocalDateTime updateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        return updateTime.format(formatter);
    }

    public static TreeCardResponse toEntity(Book book, boolean isCompletedBook, Tree tree, Seed Seed, Long memberId){
        LocalDate publishYear = book.getPublishDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return TreeCardResponse.builder()
                .cardType(0)
                .bookCategory(book.getBookCategory().getName())
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor())
                .bookTranslator(book.getTranslator())
                .bookPublisher(book.getPublisher())
                .bookPublishedYear(publishYear.format(formatter))
                .bookCompleted(isCompletedBook)
                .cardImage(book.getImageFile())
                .seedKorName(Seed.getKorName())
                .leafCount((long) tree.getLeaf().size())
                .treeTitle(tree.getTitle())
                .visibility(tree.getVisibility())
                .updateTime(TreeCardResponse.changeUpdateTime(tree.getUpdateTime()))
                .build();
    }

    public static TreeCardResponse toEntity(String coverImage, Member member, Tree tree, Seed seed, String nickname){
        return TreeCardResponse.builder()
                .cardImage(coverImage)
                .bookAuthor(nickname)
                .seedKorName(seed.getKorName())
                .visibility(tree.getVisibility())
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
