package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LeafBook {
    private Long leafId; // TODO: entity 수정
	private Long startPage;
	private Long endPage;
}