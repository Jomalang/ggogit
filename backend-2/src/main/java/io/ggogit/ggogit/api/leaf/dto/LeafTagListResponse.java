package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafTagListResponse {

    private List<LeafTagResponse> tags;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    public static LeafTagListResponse of(Page<LeafTag> leafTags) {

        List<LeafTagResponse> tags = leafTags.stream()
                .map(LeafTagResponse::of)
                .toList();

        return LeafTagListResponse.builder()
                .tags(tags)
                .totalPages(leafTags.getTotalPages())
                .totalElements(leafTags.getTotalElements())
                .currentPage(leafTags.getNumber())
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LeafTagResponse {
        private Long id;
        private String name;

        public static LeafTagResponse of(LeafTag leafTag) {
            return new LeafTagResponse(leafTag.getId(), leafTag.getName());
        }
    }
}
