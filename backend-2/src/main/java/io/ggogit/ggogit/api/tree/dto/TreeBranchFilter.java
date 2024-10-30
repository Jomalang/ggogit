package io.ggogit.ggogit.api.tree.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class TreeBranchFilter {
    @Nullable
    private Boolean bookMark;
    @NotNull
    @Max(14)
    @Min(10)
    private Long filter;
    @NotNull
    @Max(2)
    @Min(1)
    private Long sort;
    @NotNull
    private int page;
}
