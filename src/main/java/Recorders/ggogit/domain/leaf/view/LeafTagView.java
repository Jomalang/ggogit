package Recorders.ggogit.domain.leaf.view;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTagView {
    private Long id;
    private Long memberId;
    private String name;
}