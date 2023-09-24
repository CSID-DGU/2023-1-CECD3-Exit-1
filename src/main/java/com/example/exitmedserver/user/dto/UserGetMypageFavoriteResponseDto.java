package com.example.exitmedserver.user.dto;

import lombok.Data;

@Data
public class UserGetMypageFavoriteResponseDto {
    private Long pillItemSequence;
    private String pillName;
    private String image;
    private String classification;
}
