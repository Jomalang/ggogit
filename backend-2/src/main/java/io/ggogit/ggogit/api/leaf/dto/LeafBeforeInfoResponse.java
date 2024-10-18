package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeafBeforeInfoResponse {
    private Long leafId;
    private String title;
    private String createDate;
    private List<LeafTagDto> tags;

    public static LeafBeforeInfoResponse of(Leaf leaf, List<LeafTag> leafTags) {
        return  LeafBeforeInfoResponse.builder()
                .leafId(leaf.getId())
                .title(leaf.getTitle())
                .createDate(leaf.getCreateTime().toLocalDate().toString())
                .tags(leafTags.stream().map(LeafTagDto::of).toList())
                .build();
    }
}