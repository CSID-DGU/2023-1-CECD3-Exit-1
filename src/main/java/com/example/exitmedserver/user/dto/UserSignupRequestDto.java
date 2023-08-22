package com.example.exitmedserver.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSignupRequestDto {

    @NotNull
    private String userId;

    @NotNull
    private String userPassword;

    @NotNull
    private String fullName;

    @NotNull
    private int dateOfBirth;

    private String sex;

    private boolean isPregnant;
}
