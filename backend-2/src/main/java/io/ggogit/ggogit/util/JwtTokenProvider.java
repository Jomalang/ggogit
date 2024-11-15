package io.ggogit.ggogit.util;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey; // 비밀 키

    @Value("${jwt.access-expiration}") // 30분
    private long accessExpirationTime;

    @Value("${jwt.refresh-expiration}") // 7일
    private long refreshExpirationTime;

    public String generateToken(Member member, Boolean isRefreshToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", member.getId());
        claims.put("username", member.getUsername());
        claims.put("nickname", member.getNickname());
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRole());

        long expirationTime = isRefreshToken ? refreshExpirationTime : accessExpirationTime; // 만료 시간 설정
        return createToken(claims, member.getEmail(), expirationTime);
    }

    private String createToken(Map<String, Object> claims, String email, Long expirationTime) {
        return Jwts.builder()
                .setClaims(claims) // 사용자 정보
                .setSubject(email) // 고유 식별 값
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 시간
                .signWith(getBase64SecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 토큰 유효성 검사
     * return : 토큰이 만료되었는지 여부
     * - true : 유효
     * - false : 만료
     */
    public Boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getBase64SecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateAccessToken(Member member) {
        return generateToken(member, false);
    }

    public String generateRefreshToken(Member member) {
        return generateToken(member, true);
    }

    public Long getExpiration(String accessToken) {
        return extractAllClaims(accessToken).getExpiration().getTime();
    }

    public Long getMemberIdFromToken(String accessToken) {
        return Long.parseLong(extractAllClaims(accessToken).get("id").toString());
    }

    public String getEmailFromToken(String accessToken) {
        return extractAllClaims(accessToken).getSubject();
    }


    private Key getBase64SecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}