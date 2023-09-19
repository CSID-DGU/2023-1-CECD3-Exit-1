package com.example.exitmedserver.util.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.exitmedserver.user.auth.PrincipalDetails;
import com.example.exitmedserver.user.entity.User;
import com.example.exitmedserver.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

// 권한이나 인증이 필요한 링크 접속시 아래의 doFilterInternal메소드 실행
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwtHeader = request.getHeader("Authorization");

        // header가 있는지 확인
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        // 이렇게 받은 JWT의 헤더를 디코딩해서 정상적인 사용자인지 확인한다
        String token = jwtHeader.replace("Bearer ", "");

        JwtProvider jwtProvider = new JwtProvider();

        String userId = jwtProvider.getUserIdFromToken(token);

        // 서명이 정상적으로 됨
        if (userId != null) {
            User user = userRepository.findByUserId(userId);
            System.out.println("userId 정상");
            System.out.println(user.getUserId());
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            System.out.println(principalDetails.getAuthorities().size());

            // Authentication 객체를 만들어준다
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            System.out.println(authentication.isAuthenticated());

            // 강제로 spring security의 세션에 접근하여 위에서 만든 authentication 객체를 저장함
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        super.onSuccessfulAuthentication(request, response, authResult);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        super.onUnsuccessfulAuthentication(request, response, failed);
    }
}
