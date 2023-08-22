package com.example.exitmedserver.pill.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class PillImage {
    @Id
    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(name = "pill_name")
    private String pillName;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "shape")
    private String shape;
}
