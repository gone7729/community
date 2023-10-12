package project.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .contentSecurityPolicy("script-src 'self' https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js https://cdn.jsdelivr.net 'sha256-vtm7s3/ASxI/uPdnq5g1/6Vg/phsyTF4zC6c4dqIMUI=' https://cdn.quilljs.com/1.3.6/quill.js 'sha256-Bo/qm9B9BMMZ83ow454fHGkfSUHeUFiHyEB4gbmerLU=' 'nonce-my_nonce' https://code.jquery.com;");
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/emailtest", "/nicktest").permitAll();
    }
}