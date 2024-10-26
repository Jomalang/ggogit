package io.ggogit.ggogit.domain.aop;

import io.ggogit.ggogit.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 사용자 권한 확인 Aspect
     * @param memberId: 사용자 ID
     * @param accessToken: 사용자 토큰
     */
    @Before(value = "execution(* io.ggogit.ggogit.api..*Controller.*(..)) && args(memberId, accessToken, ..)", argNames = "memberId, accessToken")
    public void checkAuthorization(Long memberId, String accessToken) {

        Long tokenMemberId = jwtTokenProvider.getMemberIdFromToken(resolveToken(accessToken));

        if (!memberId.equals(tokenMemberId)) {
            throw new AccessDeniedException("권한이 없습니다.");
        }
    }

    private String resolveToken(String accessToken) {
        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
            return accessToken.substring(7);
        }
        throw new AccessDeniedException("토큰이 존재하지 않습니다.");
    }
}