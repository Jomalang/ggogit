package io.ggogit.ggogit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTemplateTest {


    @Test
    @DisplayName("테스트 이름")
    void joinEmail() {
        // given

        // when
        String email = EmailTemplate.joinEmail("http://ggogit.taecobug.io:");

        // then
        System.out.println(email);
    }
}