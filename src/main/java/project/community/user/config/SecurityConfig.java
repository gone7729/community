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
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/account","/boardpaging","/posting").permitAll() //공개페이지
                .antMatchers("/member","/write","/update").authenticated() //로그인 시 이용가
                //.antMatchers("/member","/write","/update").hasAuthority("ROLE_user") //로그인 시 이용가
                .and()
                .formLogin()
            .loginPage("/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // 로그아웃 후 세션 초기화 설정
                .deleteCookies("JSESSIONID") // 로그아웃 후 쿠기 삭제 설정
                .permitAll();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
