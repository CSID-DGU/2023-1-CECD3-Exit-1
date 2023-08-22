package com.example.exitmedserver.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class FavoriteList {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;
}
