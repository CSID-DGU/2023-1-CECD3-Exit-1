package com.example.exitmedserver.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSignupResponseDto {
    private String userId;
    private String fullName;

    @Builder
    public UserSignupResponseDto(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }
}
