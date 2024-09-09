package Recorders.ggogit.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // 실행 사이트
        // localhost:8080/swagger-ui.html
        return new OpenAPI()
                .info(new Info()
                        .title("GGOGIT API")
                        .version("1.0")
                        .description("API documentation for GGOGIT project"));
    }
}