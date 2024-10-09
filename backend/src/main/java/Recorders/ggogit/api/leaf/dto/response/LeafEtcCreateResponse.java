package Recorders.ggogit.api.leaf.dto.response;

import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafEtcCreateResponse {

    private Long leafId;
    private String message;

    public static LeafEtcCreateResponse of(LeafEtcView leafEtcView) {
        return LeafEtcCreateResponse.builder()
                .leafId(leafEtcView.getLeafId())
                .message("리프가 생성되었습니다.")
                .build();
    }
}
