package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillGetPillDetailInfoResponseDto {
    private String pillName;
    private boolean isPregnant;
    private int age;
    private boolean isFavorite;
    private String imageLink;
    private String dosage;
    private String warning;
    private String classification;
    private String effect;
    private String storage;
    private String ingredient;
}
