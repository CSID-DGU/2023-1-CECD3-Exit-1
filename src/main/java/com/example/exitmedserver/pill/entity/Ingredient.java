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
    @Column(columnDefinition = "id")
    private Integer id;

    @Column(columnDefinition = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(columnDefinition = "pill_name")
    private String pillName;

    @Column(columnDefinition = "unit_medicine")
    private String unitMedicine;

    @Column(columnDefinition = "dosage")
    private float dosage;

    @Column(columnDefinition = "unit")
    private String unit;

    @Builder
    public Ingredient(Integer id, Long pillItemSequence, String pillName, String unitMedicine, float dosage, String unit) {
        this.id = id;
        this.pillItemSequence = pillItemSequence;
        this.pillName = pillName;
        this.unitMedicine = unitMedicine;
        this.dosage = dosage;
        this.unit = unit;
    }
}
