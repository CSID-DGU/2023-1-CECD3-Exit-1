package com.example.exitmedserver.pill.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MaxIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_ingredient")
    private String mainIngredient;

    @Column(name = "max_dosage")
    private Float maxDosage;

    @Column(name = "unit")
    private String unit;

    @Builder
    public MaxIngredient(String mainIngredient, Float maxDosage, String unit) {
        this.mainIngredient = mainIngredient;
        this.maxDosage = maxDosage;
        this.unit = unit;
    }
}
