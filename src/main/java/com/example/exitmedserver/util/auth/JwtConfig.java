package com.example.exitmedserver.util.auth;

public class JwtConfig {
    public String subject = "exitToken";
    public String secret = "exit";
    public Long accessExpirationTime = 60 * 60 * 24 * 7 * 4 * 1000L;
    public int refreshExpirationTime = 60 * 60 * 24 * 7 * 1000;
}
