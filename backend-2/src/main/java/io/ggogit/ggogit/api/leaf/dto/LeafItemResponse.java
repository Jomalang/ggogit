package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.type.LeafDirectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeafItemResponse {

    private Long id;
    private Long parentLeafId;
    private String title;
    private List<LeafTagDto> tags;
    private Boolean focused; // 현재 사용자가 보고 있는 리프인지
    private LeafDirectionType direction;
    private LocalDateTime createTime;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LeafTagDto {
        private Long id;
        private String name;
    }
}