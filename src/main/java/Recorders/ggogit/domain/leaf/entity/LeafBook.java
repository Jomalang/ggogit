package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBook {
    private Long leafId; // TODO: entity 수정
	private Integer startPage;
	private Integer endPage;
}