package io.ggogit.ggogit.domain.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiBookDto {
    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String description;
    private String isbn;
    private String isbn13;
    private Long itemId;
    private Integer priceSales;
    private Integer priceStandard;
    private String mallType;
    private String stockStatus;
    private Integer mileage;
    private String cover;
    private Integer categoryId;
    private String categoryName;
    private String publisher;
    private Integer salesPoint;
    private Boolean adult;
    private Boolean fixedPrice;
    private Integer customerReviewRank;
    private SeriesInfo seriesInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SeriesInfo {
        private int seriesId;
        private String seriesLink;
        private String seriesName;
    }
}
