package com.example.exitmedserver.pill;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Pill {
    @Id
    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(name = "pill_name")
    private String pillName;

    @Getter
    @Column(name = "company")
    private String company;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "effect")
    private String effect;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "warning")
    private String warning;

    @Column(name = "storage")
    private String storage;

    @Column(name = "best_before")
    private String bestBefore;

    @Column(name = "classification")
    private String classification;
}
