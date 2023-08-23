package com.example.exitmedserver.pill.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Drawer {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;
}
