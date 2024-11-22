package io.ggogit.ggogit.api.tree.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditResponse {
    String message;
    Integer statusCode;

    public static EditResponse of(String message, Integer statusCode) {
        return EditResponse.builder()
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}
