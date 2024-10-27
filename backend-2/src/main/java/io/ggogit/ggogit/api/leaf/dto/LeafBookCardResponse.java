package io.ggogit.ggogit.api.leaf.dto;


import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class LeafBookCardResponse {

    List<ItemDto> items;
    // 총 페이지 수
    Integer totalPage;
    // 현재 페이지
    Integer currentPage;
    // 사이즈
    Integer size;

    public LeafBookCardResponse(Integer totalPage, Integer  currentPage, Integer size) {
        this.items = new ArrayList<>();
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.size = size;
    }

    public void addItem(ItemDto dto) {
        this.items.add(dto);
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemDto {
        // 도서 카테고리
        String bookCategory;
        // 도서 제목
        String bookTitle;
        // 도서 이미지
        String bookImage;
        // 트리 씨앗 값
        Long treeSeedId;
        // 트리 씨앗 값
        String treeSeed;
        // 트리 제목
        String treeTitle;
        // 트리 이미지
        String treeImage;
        // 도서 출판 날짜
        String bookPublishDate;
        // 도서 출판사
        String bookPublisher;
        // 도서 작가
        String bookAuthor;
        // 리프 제목
        String leafTitle;
        // 리프 내용
        String leafContent;
        // 리프 작성 날짜
        String leafWriteDate;
        // 리프 조회수
        Integer leafViewCount;

        public static ItemDto of(Leaf leaf, Tree tree, Book book, BookCategory bookCategory) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return ItemDto.builder()
                    .bookCategory(bookCategory.getName())
                    .bookTitle(book.getTitle())
                    .bookImage(book.getImageFile())
                    .treeSeedId(tree.getSeed().getId())
                    .treeSeed(tree.getSeed().getKorName())
                    .treeTitle(tree.getTitle())
                    .treeImage(null)
                    .bookPublishDate(book.getPublishDate().format(formatter))
                    .bookPublisher(book.getPublisher())
                    .bookAuthor(book.getAuthor())
                    .leafTitle(leaf.getTitle())
                    .leafContent(leaf.getContent())
                    .leafWriteDate(leaf.getUpdateTime().format(formatter))
                    .leafViewCount(leaf.getViewCount())
                    .build();
        }

        public static ItemDto of(Leaf leaf, Tree tree, TreeImage treeImage) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return ItemDto.builder()
                    .bookCategory(null)
                    .bookTitle(null)
                    .bookImage(null)
                    .treeSeedId(tree.getSeed().getId())
                    .treeSeed(tree.getSeed().getKorName())
                    .treeTitle(tree.getTitle())
                    .treeImage(treeImage == null ? null : treeImage.getName())
                    .bookPublishDate(null)
                    .bookPublisher(null)
                    .bookAuthor(null)
                    .leafTitle(leaf.getTitle())
                    .leafContent(leaf.getContent())
                    .leafWriteDate(leaf.getUpdateTime().format(formatter))
                    .leafViewCount(leaf.getViewCount())
                    .build();
        }
    }
}