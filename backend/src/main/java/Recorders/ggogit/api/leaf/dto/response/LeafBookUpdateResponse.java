package Recorders.ggogit.api.leaf.dto.response;

import Recorders.ggogit.domain.leaf.view.LeafBookView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBookUpdateResponse {

    private Long leafId;
    private Integer startPage;
    private Integer endPage;
    private List<Long> tagIds;
    private String title;
    private String content;
    private Boolean visibility;

    public static LeafBookUpdateResponse of(LeafBookView leafBookView) {
        return LeafBookUpdateResponse.builder()
                .leafId(leafBookView.getLeafId())
                .startPage(leafBookView.getStartPage())
                .endPage(leafBookView.getEndPage())
                .tagIds(leafBookView.getTagIds())
                .title(leafBookView.getTitle())
                .content(leafBookView.getContent())
                .visibility(leafBookView.getVisibility())
                .build();
    }
}