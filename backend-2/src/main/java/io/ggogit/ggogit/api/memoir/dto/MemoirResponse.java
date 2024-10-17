package io.ggogit.ggogit.api.memoir.dto;

import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoirResponse {
    @NotNull(message = "제목을 입력해 주세요.")
    private String title;
    @NotNull(message = "내용을 입력해 주세요.")
    private String text;
    @NotNull(message = "공개여부를 설정해주세요.")
    private Boolean visibility;
    private Boolean isOwner;
    private String message;

    public static MemoirResponse of(Memoir memoir, String message) {
        return MemoirResponse.builder()
                .title(memoir.getTitle())
                .text(memoir.getText())
                .visibility(memoir.getVisibility())
                .message(message).build();
    }

    public void ChangeOwnership(boolean ownership) {
        this.isOwner = ownership;
    }
}
