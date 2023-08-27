package com.example.exitmedserver.util.auth;

import com.example.exitmedserver.user.auth.PrincipalDetails;
import com.example.exitmedserver.user.dto.UserLoginRequestDto;
import com.example.exitmedserver.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// 로그인 시 DB의 정보와 로그인 정보를 확인하여 JWT를 생성한 후 response
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // Set your custom login URL here
        setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("attempt");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserLoginRequestDto userLoginRequestDto = objectMapper.readValue(request.getInputStream(), UserLoginRequestDto.class);
            System.out.println(userLoginRequestDto);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUserId(), userLoginRequestDto.getUserPassword());
            System.out.println(usernamePasswordAuthenticationToken);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            System.out.println(authentication);
            return authentication;
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        JwtProvider jwtProvider = new JwtProvider();

        String jwtToken = jwtProvider.createAccessToken(principalDetails.getUsername(), principalDetails.getPassword());

        response.getWriter().write("{\n\t\"Authorization\": " + "Bearer " + jwtToken + "\n}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
