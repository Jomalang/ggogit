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

    private List<LeafTagDto> tags;
    private int totalPages;
    private int currentPage;
    private int size;
    private long totalElements;
    private String message;

    public static LeafTagListResponse of(Page<LeafTag> leafTagsPage, String message) {

        List<LeafTagDto> tags = leafTagsPage.stream()
                .map(LeafTagDto::of)
                .toList();

        return LeafTagListResponse.builder()
                .tags(tags)
                .totalPages(leafTagsPage.getTotalPages())
                .totalElements(leafTagsPage.getTotalElements())
                .currentPage(leafTagsPage.getNumber() + 1)
                .size(leafTagsPage.getSize())
                .message(message)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LeafTagDto {
        private Long id;
        private String name;

        public static LeafTagDto of(LeafTag leafTag) {
            return new LeafTagDto(leafTag.getId(), leafTag.getName());
        }
    }
}
