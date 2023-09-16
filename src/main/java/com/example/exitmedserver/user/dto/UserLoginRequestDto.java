package com.example.exitmedserver.user.dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String userId;
    private String userPassword;
}
