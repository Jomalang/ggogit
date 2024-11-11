package io.ggogit.ggogit.api.tree.dto;


import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeCardDtoResponse {


    @Builder.Default
    private List<TreeCardDtoResponse.itemDto> items = new ArrayList<>();
    private Integer totalPage;
    // 현재 페이지
    private Integer currentPage;
    // 사이즈
    private Integer size;

    public static TreeCardDtoResponse of(Page<Tree> trees) {

        List<TreeCardDtoResponse.itemDto> items = new ArrayList<>();

        for(Tree t : trees.getContent()){
            Member member = t.getMember();
            TreeCardDtoResponse.itemDto item = TreeCardDtoResponse.itemDto.of(t, member);
            items.add(item);
        }

        return TreeCardDtoResponse.builder()
                .totalPage(trees.getTotalPages())
                .currentPage(trees.getNumber())
                .size(trees.getSize())
                .items(items)
                .build();
    }

    public void addItem(TreeCardDtoResponse.itemDto dto) {
        this.items.add(dto);
    }



    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class itemDto{

        @Builder.Default
        private int cardType = 0;

        private String title;
        private String content;
        private String updateDate;
        private Integer leafCount;
        private Integer viewCount;
        private String nickname;
        private String emailId;

        public static TreeCardDtoResponse.itemDto of(Tree tree, Member member) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return TreeCardDtoResponse.itemDto.builder()
                    .title(tree.getTitle())
                    .content(tree.getDescription())
                    .updateDate(tree.getUpdateTime().format(formatter))
                    .viewCount(tree.getLeaf().stream().mapToInt(Leaf::getViewCount).sum())
                    .leafCount(tree.getLeaf().size())
                    .nickname(member.getNickname())
                    .emailId("@"+member.getEmail().split("@")[0])
                    .build();
        }
    }
}
