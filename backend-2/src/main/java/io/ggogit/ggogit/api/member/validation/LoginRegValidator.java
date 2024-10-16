package io.ggogit.ggogit.api.member.validation;

import Recorders.ggogit.domain.member.service.LoginService;
import Recorders.ggogit.web.member.form.LoginRegForm;
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

    private final LoginService loginService;

    //검증가능한 대상(LoginRedForm.class)인지 검사한다.
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRegForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRegForm loginRegForm = (LoginRegForm) target;

        //유효성 검증 로직 시작

        //동의 하지 않은 경우 검증
        if(loginRegForm.getPolicyAgreement() != null && !loginRegForm.getPolicyAgreement()){
            errors.rejectValue("policyAgreement", "NotAgree");
            log.info("errors: {}", errors.getAllErrors());
        }

        //이메일
        if(loginRegForm.getEmail() != null){
            String email = loginRegForm.getEmail();
            String ValidEmail = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

            //중복되는 이메일 검증
            if(loginService.getMemberByEmail(email) != null){
                errors.rejectValue("email","Duplicate");
            }
            // 이메일 유효성 확인
            else if(!Pattern.matches(ValidEmail, email)){
                errors.rejectValue("email","NotValid");
            }
        }

        //닉네임
        if(loginRegForm.getNickname() != null){
            String nickname = loginRegForm.getNickname();
            String ValidNickname = "^[a-zA-Z0-9가-힣_]+$";
            //중복되는 닉네임 검증
            if(loginService.getMemberByNickname(nickname) != null){
                errors.rejectValue("nickname","Duplicate");
            }
            // 닉네임 유효성 확인
            else if(!Pattern.matches(ValidNickname, nickname)){
                errors.rejectValue("nickname","NotValid");
            }
            //TODO 닉네임 길이 검사
        }

        //비밀번호
        if(loginRegForm.getPassword() != null){
            //비밀번호 유효성 확인
            String ValidPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()?\\-_+=\\[\\]{}|;:,.<>]).{8,32}$";
            if(!Pattern.matches(ValidPassword, loginRegForm.getPassword())){
                errors.rejectValue("password","NotValid");
            }
            //TODO 허용되지 않는 특수문자 명시해주기
        }
    }
}
