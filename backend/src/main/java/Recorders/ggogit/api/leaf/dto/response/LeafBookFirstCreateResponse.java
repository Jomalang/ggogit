package Recorders.ggogit.api.leaf.dto.response;

import Recorders.ggogit.domain.leaf.view.LeafBookView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBookFirstCreateResponse {

    private Long leafId;
    private String message;

    public static LeafBookFirstCreateResponse of(LeafBookView leafBookView) {
        return LeafBookFirstCreateResponse.builder()
                .leafId(leafBookView.getLeafId())
                .message("리프가 생성되었습니다.")
                .build();
    }
}