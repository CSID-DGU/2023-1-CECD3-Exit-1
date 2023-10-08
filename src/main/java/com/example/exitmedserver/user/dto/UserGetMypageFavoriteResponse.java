package com.example.exitmedserver.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserGetMypageFavoriteResponse {
    private List<UserGetMypageFavoriteResponseDto> data;
}
