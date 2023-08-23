package com.example.exitmedserver.util.config;

import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // 서버가 json을 응답할 때 자바스크립트가 처리할 수 있도록 허용
        configuration.addAllowedOrigin("*"); // 모든 ip에 응답을 허용하겠다
        configuration.addAllowedHeader("*"); // 모든 헤더에 응답을 허용하겠다
        configuration.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용하겠다
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                return configuration;
            }
        };
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // AuthenticationManager 생성
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests

                        // auth로 시작하는 url은 admin, user만 접근 가능
                        //.requestMatchers("/auth").hasAnyRole("ADMIN", "USER")

                        // 이외의 링크는 모두 접근 가능
                        .anyRequest().permitAll())

                // form login 비활성화
                .formLogin(AbstractHttpConfigurer::disable)

                // http basic으로 인증(ID, PW를 통한 인증) 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)

                //csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                // 세션과 쿠키를 이용한 인증 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 위에서 생성한 authenticationManager 사용 설정
                .authenticationManager(authenticationManager)
                ;
        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
