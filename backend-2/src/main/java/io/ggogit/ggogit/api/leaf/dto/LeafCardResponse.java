package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafCardResponse {

    List<ItemDto> items;
    Integer totalPage;
    Integer currentPage;
    Integer size;

    public LeafCardResponse(int totalPage, int currentPage, int size) {
        this.items = new ArrayList<>();
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.size = size;
    }

    public void addItem(ItemDto itemDto) {
        this.items.add(itemDto);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemDto {
        @Builder.Default
        int cardType = 2;

        Long id;
        Long bookId;
        String title;
        String content;
        String updateDate;
        Integer viewCount;
        String nickname;
        String emailId;

        public static ItemDto of(Leaf leaf, Tree tree, Member member) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return ItemDto.builder()
                    .id(leaf.getId())
                    .bookId(tree.getBook().getId())
                    .title(leaf.getTitle())
                    .content(leaf.getContent())
                    .updateDate(leaf.getUpdateTime().format(formatter))
                    .viewCount(leaf.getViewCount())
                    .nickname(member.getNickname())
                    .emailId("@"+member.getEmail().split("@")[0])
                    .build();
        }
    }
}