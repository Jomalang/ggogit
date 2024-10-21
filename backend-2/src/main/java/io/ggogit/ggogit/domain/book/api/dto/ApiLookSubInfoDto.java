package io.ggogit.ggogit.domain.book.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiLookSubInfoDto {
    private String subTitle;
    private String originalTitle;
    private int itemPage;
}
