package com.example.exitmedserver.util.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtTokenInfo {
    private String accessToken;
    private String refreshToken;
}
