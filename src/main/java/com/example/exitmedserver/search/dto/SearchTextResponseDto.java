package com.example.exitmedserver.search.dto;

import lombok.Data;

@Data
public class SearchTextResponseDto {
    private Long pillItemSequence;
    private String pillName;
    private String image;
    private String classification;
}
