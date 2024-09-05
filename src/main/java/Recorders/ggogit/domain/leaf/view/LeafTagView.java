package Recorders.ggogit.domain.leaf.view;


import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTagView {
    private Long id;
    private Long memberId;
    private String name;

    public static LeafTagView of (LeafTag leafTag) {
        return builder()
                .id(leafTag.getId())
                .memberId(leafTag.getMemberId())
                .name(leafTag.getName())
                .build();
    }
}