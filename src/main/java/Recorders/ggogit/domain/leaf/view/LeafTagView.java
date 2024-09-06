package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTagView {
    private Long id;
    private String name;
    private Long priority;

    public static LeafTagView of(LeafTag leafTag) {
        return LeafTagView.builder()
                .id(leafTag.getId())
                .name(leafTag.getName())
                .priority(1L) // 일반 조회
                .build();
    }
}