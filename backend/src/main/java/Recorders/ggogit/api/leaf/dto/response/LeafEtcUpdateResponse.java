package Recorders.ggogit.api.leaf.dto.response;

import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafEtcUpdateResponse {

    private Long leafId;
    private List<Long> tagIds;
    private String title;
    private String content;
    private Boolean visibility;

    public static LeafEtcUpdateResponse of(LeafEtcView leafEtcView) {
        return LeafEtcUpdateResponse.builder()
                .leafId(leafEtcView.getLeafId())
                .tagIds(leafEtcView.getTagIds())
                .title(leafEtcView.getTitle())
                .content(leafEtcView.getContent())
                .visibility(leafEtcView.getVisibility())
                .build();

    }
}