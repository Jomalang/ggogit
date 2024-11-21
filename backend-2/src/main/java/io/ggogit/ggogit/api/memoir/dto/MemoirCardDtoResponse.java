package io.ggogit.ggogit.api.memoir.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
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
public class MemoirCardDtoResponse {

    @Builder.Default
    private List<itemDto> items = new ArrayList<>();
    private Integer totalPage;
    // 현재 페이지
    private Integer currentPage;
    // 사이즈
    private Integer size;

    public static MemoirCardDtoResponse of(Page<Tree> trees) {

        List<itemDto> items = new ArrayList<>();

        for(Tree t : trees.getContent()){
            Memoir m = t.getMemoir();
            Member member = t.getMember();
            MemoirCardDtoResponse.itemDto item = MemoirCardDtoResponse.itemDto.of(m, t, member);
            items.add(item);
        }

        return MemoirCardDtoResponse.builder()
                .totalPage(trees.getTotalPages())
                .currentPage(trees.getNumber())
                .size(trees.getSize())
                .items(items)
                .build();
    }

    public void addItem(itemDto dto) {
        this.items.add(dto);
    }



    @Getter @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class itemDto{

        @Builder.Default
        private int cardType = 1;

        private Long id;
        private String title;
        private String content;
        private String updateDate;
        private Integer leafCount;
        private Integer viewCount;
        private String nickname;
        private String emailId;

        public static itemDto of(Memoir memoir, Tree tree, Member member) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return itemDto.builder()
                    .id(memoir.getId())
                    .title(memoir.getTitle())
                    .content(memoir.getText())
                    .updateDate(memoir.getUpdateTime().format(formatter))
                    .viewCount(memoir.getViewCount())
                    .leafCount(tree.getLeaf().size())
                    .nickname(member.getNickname())
                    .emailId("@"+member.getEmail().split("@")[0])
                    .build();
        }
    }
}
