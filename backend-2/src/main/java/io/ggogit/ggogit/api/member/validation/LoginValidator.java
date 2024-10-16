package io.ggogit.ggogit.api.member.validation;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.service.LoginService;
import Recorders.ggogit.web.member.form.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginValidator implements Validator {

    private final LoginService loginService;
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;
        Member member = loginForm.toMember();

        //검증 로직
        if(loginService.login(member) == null) {
            errors.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

    }
}
