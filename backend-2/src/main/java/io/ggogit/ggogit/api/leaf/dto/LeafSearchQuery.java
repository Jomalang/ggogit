package io.ggogit.ggogit.api.leaf.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;

@Data
@Builder
public class LeafSearchQuery {
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
    @Max(14)
    @Min(0)
    private Long filter;

    @NotNull
    private String searchFilter;
}
