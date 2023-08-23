package com.example.exitmedserver.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Builder
    public User(String userId, String userPassword, Timestamp createdAt) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.createdAt = createdAt;
    }
}
