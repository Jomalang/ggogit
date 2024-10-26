package io.ggogit.ggogit.filter;

import io.ggogit.ggogit.util.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    /**
     * JWT 토큰 유효성 검사 필터
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        // JWT 토큰 유효성 검사
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) { // 토큰이 존재하고 유효한 경우
            String email = jwtTokenProvider.getEmailFromToken(token); // 토큰에서 이메일 추출
            UserDetails userDetails = userDetailsService.loadUserByUsername(email); // 이메일로 사용자 정보 조회
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // 사용자 정보로 인증 객체 생성
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // 인증 객체에 요청 정보 추가
            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 객체를 시큐리티 컨텍스트에 저장
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
