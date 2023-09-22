package com.example.exitmedserver.search.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class SearchHistoryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "search_text")
    private String searchText;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Builder
    public SearchHistoryList(Integer id, String userId, String searchText, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.searchText = searchText;
        this.createdAt = createdAt;
    }
}
