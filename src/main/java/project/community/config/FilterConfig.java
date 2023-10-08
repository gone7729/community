package project.community.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;


@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<Filter> myFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter());
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 지정
        return registrationBean;
    }
}
