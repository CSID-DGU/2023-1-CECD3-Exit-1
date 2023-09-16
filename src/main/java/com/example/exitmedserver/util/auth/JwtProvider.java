package com.example.exitmedserver.util.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtProvider {
    private final JwtConfig jwtConfig = new JwtConfig();
    public String createAccessToken(String userId, String userPassword) {
        return JWT.create()
                .withSubject(jwtConfig.subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.accessExpirationTime))
                .withClaim("userId", userId)
                .withClaim("userPassword", userPassword)
                .sign(Algorithm.HMAC512(jwtConfig.secret));
    }

    public String getUserIdFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtConfig.secret))
                .build()
                .verify(token)
                .getClaim("userId")
                .asString();
    }
}
