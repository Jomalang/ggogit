package io.ggogit.ggogit.api.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCheckTokenRequest {
    @NotBlank(message = "key은 필수 입력 값입니다.")
    private String key;
}
