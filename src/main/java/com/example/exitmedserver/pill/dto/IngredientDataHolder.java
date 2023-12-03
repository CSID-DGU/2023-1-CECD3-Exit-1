package com.example.exitmedserver.pill.dto;

import lombok.Data;

@Data
public class IngredientDataHolder {
    private String ingredientName;
    private Float dosage;
    private String unit;
    private Integer cntPerDosage;
    private Integer cntPerDay;
}
