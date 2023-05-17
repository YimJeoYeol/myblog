package com.example.myblog;

import com.example.myblog.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaAuditing
@RequiredArgsConstructor
@SpringBootApplication
public class MyblogApplication {
    private final PrincipalDetailService principalDetailService;
    @Bean
    public BCryptPasswordEncoder encoderPW(){
        return new BCryptPasswordEncoder();
    }
    protected void config(AuthenticationManagerBuilder builder)throws Exception{
        builder.userDetailsService(principalDetailService).passwordEncoder(encoderPW());
    }
    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }

}
