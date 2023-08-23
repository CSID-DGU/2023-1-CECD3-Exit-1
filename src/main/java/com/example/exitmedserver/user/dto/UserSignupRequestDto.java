package com.example.exitmedserver.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSignupRequestDto {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String userPassword;

    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    private int dateOfBirth;

    private String sex;

    private boolean isPregnant;
}
