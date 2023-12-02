package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class PillGetMaxAllowedResponseDto {
    private String mainIngredient;
    private Float dosage;
    private Float maxDosage;
    private String unit;
}
