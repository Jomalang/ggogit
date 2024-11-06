package io.ggogit.ggogit.api.tree.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;

@Data
@Builder
public class TreeSearchQuery {
    @Nullable
    private String query;

    @NotNull
    @Min(0)
    private int page;

    @NotNull
    @Max(1)
    @Min(0)
    private Long sort;

    @NotNull
    private String filter;
}
