package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTagMap {
    private Long leafId; // TODO: entity 수정
    private Long leafTagId; // TODO: entity 수정

    public static LeafTagMap of(Long leafId, Long leafTagId) {
        return LeafTagMap.builder()
                .leafId(leafId)
                .leafTagId(leafTagId)
                .build();
    }
}