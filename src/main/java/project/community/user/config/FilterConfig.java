package project.community.user.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.community.user.Filter.LoginCheckFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoginCheckFilter> myFilter() {
        FilterRegistrationBean<LoginCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter());
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 지정
        return registrationBean;
    }
}
