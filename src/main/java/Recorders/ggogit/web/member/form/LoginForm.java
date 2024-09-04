package Recorders.ggogit.web.member.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
