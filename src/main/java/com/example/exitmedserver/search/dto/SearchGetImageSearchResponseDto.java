package com.example.exitmedserver.search.dto;

import lombok.Data;

@Data
public class SearchGetImageSearchResponseDto {
    private Long pillItemSequence;
    private String imageLink;
    private String pillName;
    private String shape;
}
