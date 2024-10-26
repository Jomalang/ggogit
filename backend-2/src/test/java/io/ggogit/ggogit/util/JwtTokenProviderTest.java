package io.ggogit.ggogit.util;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("토큰 생성 테스트")
    void generateToken() {
        // given
        Member member = Member.builder()
                .id(1L)
                .email("tset@ggogle.com")
                .username("test")
                .build();

        // when
        String token = jwtTokenProvider.generateToken(member, false);

        // then
        assertNotNull(token);
        System.out.println("token = " + token);
    }

    @Test
    @DisplayName("토큰 검증 테스트")
    void validateToken() {
        // given
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZW1haWwiOiJ0c2V0QGdnb2dsZS5jb20iLCJ1c2VybmFtZSI6InRlc3QiLCJzdWIiOiJ0c2V0QGdnb2dsZS5jb20iLCJpYXQiOjE3Mjk5NDIyNDMsImV4cCI6MTcyOTk0NDA0M30.8BLbowREjQg1AZ4thBuXYOc-ae0GKJTf8IL1EbnRfvg";

        // when
        boolean result = jwtTokenProvider.validateToken(token);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("테스트 이름")
    void generateAdminToken() {
        // given

        // when
        String token = jwtTokenProvider.generateAdminToken();

        // then
        System.out.println("token = " + token);
    }
}