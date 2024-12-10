package io.ggogit.ggogit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000", "http://localhost:8080",
                                "http://192.168.0.21:80", "https://192.168.0.21:443", "http://192.168.0.21:8080", "http://192.168.0.21:3000",
                                "https://ggogit.taecobug.io")
                        // .allowedOrigins("http://localhost:3000")
                        .allowedOriginPatterns("https://ggogit.taecobug.io")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowCredentials(true) // 쿠키 허용 (이거 없으면 CORS 에러)
                        .allowedHeaders("*");
            }
        };
    }
}