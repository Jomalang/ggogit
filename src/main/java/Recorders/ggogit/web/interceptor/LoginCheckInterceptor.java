package Recorders.ggogit.web.interceptor;

import Recorders.ggogit.web.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("loginCheckInterceptor start");
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            //TODO 리다이렉트 보내줄때 쿼리 파라미터까지 포함시켜서 보내주는 방법 구현 필요
            response.sendRedirect("/member/login?redirectURL="+requestURI);
            return false;
        }

        return true;
    }
}
