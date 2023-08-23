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

        if (userRepository.findUserByUserId(userId) != null) {
            // 만들려는 아이디가 이미 존재함, 아이디 생성 불가
            userCheckDuplicatedResponseDto.setDuplicated(true);
            return userCheckDuplicatedResponseDto;
        } else {
            // 아이디 생성 가능
            userCheckDuplicatedResponseDto.setDuplicated(false);
            return userCheckDuplicatedResponseDto;
        }
    }
}
