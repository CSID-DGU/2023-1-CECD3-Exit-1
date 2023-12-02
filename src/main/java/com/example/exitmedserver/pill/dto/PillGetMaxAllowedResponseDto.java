package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillGetMaxAllowedResponseDto {
    private String mainIngredient;
    private float maxDosage;
    private String unit;
}
