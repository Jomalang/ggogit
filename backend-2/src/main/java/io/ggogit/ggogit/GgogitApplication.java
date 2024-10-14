package io.ggogit.ggogit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GgogitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgogitApplication.class, args);
    }

}
