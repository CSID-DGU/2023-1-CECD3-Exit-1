package com.example.exitmedserver.pill.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(name = "pill_name")
    private String pillName;

    @Column(name = "unit_medicine")
    private String unitMedicine;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "dosage")
    private Float dosage;

    @Column(name = "unit")
    private String unit;

    @Builder
    public Ingredient(Integer id, Long pillItemSequence, String pillName, String unitMedicine, String ingredient, Float dosage, String unit) {
        this.id = id;
        this.pillItemSequence = pillItemSequence;
        this.pillName = pillName;
        this.unitMedicine = unitMedicine;
        this.ingredient = ingredient;
        this.dosage = dosage;
        this.unit = unit;
    }
}
