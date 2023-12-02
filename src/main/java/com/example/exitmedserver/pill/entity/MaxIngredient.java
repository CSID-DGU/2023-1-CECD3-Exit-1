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
    @Column(columnDefinition = "id")
    private Integer id;

    @Column(columnDefinition = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(columnDefinition = "main_ingredient")
    private String mainIngredient;

    @Column(columnDefinition = "max_dosage")
    private Float maxDosage;

    @Column(columnDefinition = "unit2")
    private String unit;

    @Builder
    public MaxIngredient(Integer id, Long pillItemSequence, String mainIngredient, Float maxDosage, String unit) {
        this.id = id;
        this.pillItemSequence = pillItemSequence;
        this.mainIngredient = mainIngredient;
        this.maxDosage = maxDosage;
        this.unit = unit;
    }
}
