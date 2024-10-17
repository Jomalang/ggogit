package io.ggogit.ggogit.api.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AladinApiSearchResponse {

    private int count;
    private String message;

    public static AladinApiSearchResponse of(int count, String message) {
        return AladinApiSearchResponse.builder()
                .count(count)
                .message(message)
                .build();
    }
}