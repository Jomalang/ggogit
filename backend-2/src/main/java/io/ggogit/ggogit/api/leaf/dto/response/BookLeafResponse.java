package io.ggogit.ggogit.api.leaf.dto.response;

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

    public static BookLeafResponse of(LeafBook saved, String message) {
        return BookLeafResponse.builder()
                .leafId(saved.getLeaf().getId())
                .message(message)
                .build();
    }

    public static BookLeafResponse of(Long leafId, String message) {
        return BookLeafResponse.builder()
                .leafId(leafId)
                .message(message)
                .build();
    }
}
