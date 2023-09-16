package com.example.exitmedserver.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Column(name = "user_role")
    private String userRole;

    @Builder
    public User(String userId, String userPassword, Timestamp createdAt, String userRole) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.createdAt = createdAt;
        this.userRole = userRole;
    }

    public ArrayList<String> getRoleList() {
        if (!this.userRole.isEmpty()) {
            return new ArrayList<>(Arrays.asList(this.userRole.split(",")));
        }
        return new ArrayList<String>();
    }
}
