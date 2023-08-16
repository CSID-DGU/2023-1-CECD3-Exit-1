package com.example.exitmedserver.util.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // AuthenticationManager 생성
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                // form login 비활성화
                .formLogin(AbstractHttpConfigurer::disable)

                // http basic으로 인증(ID, PW를 통한 인증) 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)

                // 세션과 쿠키를 이용한 인증 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 위에서 생성한 authenticationManager 사용 설정
                .authenticationManager(authenticationManager)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests

                        // auth로 시작하는 url은 admin, user만 접근 가능
                        .requestMatchers("/auth").hasAnyRole("ADMIN", "USER")

                        // 이외의 링크는 모두 접근 가능
                        .anyRequest().permitAll())
                ;
        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
