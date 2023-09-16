package com.example.exitmedserver.user.service;

import com.example.exitmedserver.user.dto.UserCheckDuplicatedRequestDto;
import com.example.exitmedserver.user.dto.UserCheckDuplicatedResponseDto;
import com.example.exitmedserver.user.dto.UserSignupRequestDto;
import com.example.exitmedserver.user.dto.UserSignupResponseDto;
import com.example.exitmedserver.user.entity.User;
import com.example.exitmedserver.user.entity.UserProfile;
import com.example.exitmedserver.user.repository.UserProfileRepository;
import com.example.exitmedserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserSignupResponseDto signup(UserSignupRequestDto userSignupRequestDto) {
        System.out.println("user service started");
        User user = User.builder()
                .userId(userSignupRequestDto.getUserId())
                .userPassword(bCryptPasswordEncoder.encode(userSignupRequestDto.getUserPassword()))
                .createdAt(Timestamp.from(Instant.now()))
                .userRole("ROLE_USER")
                .build();

        byte isPregnant;
        if (userSignupRequestDto.isPregnant()) {
            isPregnant = 1;
        } else {
            isPregnant = 0;
        }

        UserProfile userProfile = UserProfile.builder()
                .userId(userSignupRequestDto.getUserId())
                .fullName(userSignupRequestDto.getFullName())
                .dateOfBirth(userSignupRequestDto.getDateOfBirth())
                .profilePicture("/")
                .sex(userSignupRequestDto.getSex())
                .isPregnant(isPregnant)
                .build();

        userRepository.save(user);
        userProfileRepository.save(userProfile);

        return UserSignupResponseDto.builder()
                .userId(userSignupRequestDto.getUserId())
                .fullName(userSignupRequestDto.getFullName())
                .build();
    }

    public UserCheckDuplicatedResponseDto checkDuplicated(UserCheckDuplicatedRequestDto userCheckDuplicatedRequestDto) {
        String userId = userCheckDuplicatedRequestDto.getUserId();
        UserCheckDuplicatedResponseDto userCheckDuplicatedResponseDto = new UserCheckDuplicatedResponseDto();

        userCheckDuplicatedResponseDto.setDuplicated(userRepository.findByUserId(userId) != null);
        return userCheckDuplicatedResponseDto;
    }
}
