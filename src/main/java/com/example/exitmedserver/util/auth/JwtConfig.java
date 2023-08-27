package com.example.exitmedserver.util.auth;

public class JwtConfig {
    public String subject = "exitToken";
    public String secret = "exit";
    public int accessExpirationTime = 60 * 60 * 1000;
    public int refreshExpirationTime = 60 * 60 * 24 * 7 * 1000;
}
