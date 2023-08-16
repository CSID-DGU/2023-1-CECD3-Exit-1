package com.example.exitmedserver.search;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Getter
public class SearchHistoryList {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "pill_item_sequence")
    private Long pillItemSequence;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
