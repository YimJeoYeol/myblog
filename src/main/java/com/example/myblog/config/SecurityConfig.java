package com.example.myblog.config;

import com.example.myblog.config.oauth.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserOAuth2Service userOAuth2Service;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws  Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and() // CSRF 보안에 대한 설정. 아무 설정도 하지 않으면 CSRF 보안을 하도록 설정
                .authorizeHttpRequests() //요청에 대한 권한 지정. Security 처리에 HttpServletRequest를 이용한다는 것을 의미한다.
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll()//특정 경로를 지정->어떤 사용자든 접근 가능
                .anyRequest() //설정한 경로 외에 모든 경로를 말함
                .authenticated() // 인증된 사용자만이 접근 가능
                .and().formLogin() // form 기반의 로그인을 사용
                .loginPage("/auth/loginForm") // 로그인 페이지 url 설정
                .loginProcessingUrl("/auth/loginProc") // 로그인 Form Action Url을 지정할 수 있다. 사용자 이름과 암호를 제출할 URL (기본값은 /login 이다.)
                .defaultSuccessUrl("/")
//                .failureHandler(customFailureHandler)
                .and()
                .oauth2Login()
                .loginPage("/auth/loginForm")
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(userOAuth2Service);
        return http.build();
    }


}
