package project.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.community.config.interceptor.AsyncInterceptor;
import project.community.config.interceptor.Interceptor;
import project.community.config.interceptor.LogInterceptor;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS));
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()) // LogInterceptor 등록
                .order(1)	// 적용할 필터 순서 설정
                .addPathPatterns("/**")
                .excludePathPatterns("/error"); // 인터셉터에서 제외할 패턴
        registry.addInterceptor(new Interceptor())
                .order(2)
                .addPathPatterns(
                        "/write", "/member", "/update", "/postUpdate", "/delete"
                );
        registry.addInterceptor(new AsyncInterceptor())
                .order(3)
                .excludePathPatterns("/emailtest");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
