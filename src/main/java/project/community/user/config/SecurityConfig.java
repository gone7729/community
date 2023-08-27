package project.community.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/login","/user/account","/board/boardpaging","/board/posting").permitAll() //공개페이지
                .antMatchers("/user/member","/board/write","/board/update").authenticated() //로그인 시 이용가
                .and()
                .formLogin()
            .loginPage("/user/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/index")
                .permitAll();
    }
}
