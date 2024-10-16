package io.ggogit.ggogit.api.memoir.dto;

import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemoirRequest {

    @NotNull(message = "제목을 입력해 주세요.")
    private String title;
    @NotNull(message = "내용을 입력해 주세요.")
    private String text;
    @NotNull(message = "공개여부를 설정해주세요.")
    private Boolean visibility;

    public Memoir toMemoir() {
        return Memoir.builder()
                .title(this.title)
                .text(this.text)
                .visibility(this.visibility).build();
    }

    public boolean validate() {
       return title != null && text != null && visibility != null;
    }
}
