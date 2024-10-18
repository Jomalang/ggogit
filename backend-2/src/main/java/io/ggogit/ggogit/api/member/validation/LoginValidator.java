package io.ggogit.ggogit.api.member.validation;


import io.ggogit.ggogit.api.member.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequest loginRequest = (LoginRequest) target;

        if (loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "email.empty", "이메일을 입력해주세요.");
        } else if (!isValidEmail(loginRequest.getEmail())) {
            errors.rejectValue("email", "email.invalid", "올바른 이메일 형식이 아닙니다.");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "password.empty", "비밀번호를 입력해주세요.");
        } else if (loginRequest.getPassword().length() < 8) {
            errors.rejectValue("password", "password.tooShort", "비밀번호는 8자 이상이어야 합니다.");
        }
    }

    private boolean isValidEmail(String email) {
        // 간단한 이메일 형식 검사
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
