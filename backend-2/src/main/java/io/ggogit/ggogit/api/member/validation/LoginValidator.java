package io.ggogit.ggogit.api.member.validation;

import io.ggogit.ggogit.api.member.dto.MemberLoginRequestDto;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.LoginService;
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
        return MemberLoginRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberLoginRequestDto loginForm = (MemberLoginRequestDto) target;
        Member member = loginForm.toMember();

        //검증 로직
        if(loginService.login(loginForm) == null) {
            errors.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }
    }
}
