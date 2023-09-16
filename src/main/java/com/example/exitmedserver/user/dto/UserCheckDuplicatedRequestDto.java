package com.example.exitmedserver.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCheckDuplicatedRequestDto {
    @NotNull
    @NotEmpty
    private String userId;
}
