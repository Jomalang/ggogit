package Recorders.ggogit.web;

import Recorders.ggogit.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**")
                .order(1)
                //TODO 커뮤니티 기능 확장되면, 화이트 리스트에 추가해야 함.
                .excludePathPatterns("/","/member/login","/member/join-input","/member/logout","/common/**","/css/**","/js/**","/img/**","/svg/**");
    }
}
