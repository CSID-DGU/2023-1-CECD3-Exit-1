package com.example.exitmedserver.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class UserProfile {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private int dateOfBirth;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "sex")
    private String sex;

    @Column(name = "is_pregnant")
    private Byte isPregnant;
}
