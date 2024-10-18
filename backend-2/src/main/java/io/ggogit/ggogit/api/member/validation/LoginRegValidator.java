package io.ggogit.ggogit.api.member.validation;

import io.ggogit.ggogit.domain.member.service.LoginService;
import io.ggogit.ggogit.api.member.form.LoginRegForm;
import io.ggogit.ggogit.api.member.dto.LoginRegRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginRegValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRegRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRegRequest request = (LoginRegRequest) target;

        // 이메일 유효성 검사
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "email.empty", "이메일을 입력해주세요.");
        } else if (!isValidEmail(request.getEmail())) {
            errors.rejectValue("email", "email.invalid", "올바른 이메일 형식이 아닙니다.");
        }

        // 비밀번호 유효성 검사
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "password.empty", "비밀번호를 입력해주세요.");
        } else if (request.getPassword().length() < 8) {
            errors.rejectValue("password", "password.tooShort", "비밀번호는 8자 이상이어야 합니다.");
        }

        // 비밀번호 확인 유효성 검사 (비밀번호 확인 필드가 있다고 가정)
        if (request.getConfirmPassword() != null && !request.getConfirmPassword().equals(request.getPassword())) {
            errors.rejectValue("confirmPassword", "password.mismatch", "비밀번호가 일치하지 않습니다.");
        }

        // 이름 유효성 검사 (이름 필드가 있다고 가정)
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            errors.rejectValue("name", "name.empty", "이름을 입력해주세요.");
        }

        // 추가적인 유효성 검사 규칙들...
    }

    private boolean isValidEmail(String email) {
        // 간단한 이메일 형식 검사
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
