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
public class LoginRequest {
    @NotBlank(message = "사용자 이름이 필요합니다")
    private String username;
    @NotBlank(message = "비밀번호가 필요합니다")
    private String password;

    private String email;
}
