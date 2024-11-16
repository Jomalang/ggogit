package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLeafResponse {

    private Long leafId;
    private String message;
    private Integer statusCode;

    public static BookLeafResponse of(LeafBook saved, String message, Integer statusCode) {
        return BookLeafResponse.builder()
                .leafId(saved.getLeaf().getId())
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    public static BookLeafResponse of(Long leafId, String message, Integer statusCode) {
        return BookLeafResponse.builder()
                .leafId(leafId)
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}
