package jw.project.ecommerce.infrastructure.user.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String LOGIN_URI = "/user/login";
    private static final String SIGNUP_URI = "/user/signup";
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않는다.
                .and()
                .httpBasic().disable() //username, password 사용하는 basic 방식 X Token 사용하는 Bearer 방식
                .formLogin().disable() // 기본 제공 로그인 폼 사용하지 않는다
                .logout().disable() //
                .authorizeHttpRequests()
                .antMatchers(POST,LOGIN_URI).permitAll()
                .antMatchers(POST,SIGNUP_URI).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

