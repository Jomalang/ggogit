package io.ggogit.ggogit.api.leaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafBranchInfoResponse {
    String branchName;
    Integer leafCount;
    Integer likeCount;
    Integer viewCount;
    LocalDateTime updateTime;
}