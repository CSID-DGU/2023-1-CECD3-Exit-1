package com.example.exitmedserver.user.repository;

import com.example.exitmedserver.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    UserProfile findUserProfileByUserId(String userId);
}
