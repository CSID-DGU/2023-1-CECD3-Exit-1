package com.example.exitmedserver.user.service;

import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.pill.repository.PillImageRepository;
import com.example.exitmedserver.pill.repository.PillRepository;
import com.example.exitmedserver.search.entity.FavoriteList;
import com.example.exitmedserver.search.repository.FavoriteListRepository;
import com.example.exitmedserver.user.dto.*;
import com.example.exitmedserver.user.entity.User;
import com.example.exitmedserver.user.entity.UserProfile;
import com.example.exitmedserver.user.repository.UserProfileRepository;
import com.example.exitmedserver.user.repository.UserRepository;
import com.example.exitmedserver.util.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final FavoriteListRepository favoriteListRepository;
    private final PillRepository pillRepository;
    private final PillImageRepository pillImageRepository;

    public UserSignupResponseDto signup(UserSignupRequestDto userSignupRequestDto) {
        System.out.println("user service started");
        User user = User.builder()
                .userId(userSignupRequestDto.getUserId())
                .userPassword(bCryptPasswordEncoder.encode(userSignupRequestDto.getUserPassword()))
                .createdAt(Timestamp.from(Instant.now()))
                .userRole("ROLE_USER")
                .build();

        boolean isPregnant;
        isPregnant = userSignupRequestDto.isPregnant();

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

    public UserGetMypageFavoriteResponse getMypageFavorite(String jwtToken) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<UserGetMypageFavoriteResponseDto> mypageFavoriteList = new ArrayList<>();
        List<FavoriteList> searchedFavoriteList = favoriteListRepository.findFavoriteListByUserId(userId);

        if (!searchedFavoriteList.isEmpty()) {
            for (FavoriteList f : searchedFavoriteList) {
                UserGetMypageFavoriteResponseDto userGetMypageFavoriteResponseDto = new UserGetMypageFavoriteResponseDto();
                Optional<Pill> pillResult = pillRepository.findById(f.getPillItemSequence());
                Pill pill = pillResult.get();
                userGetMypageFavoriteResponseDto.setPillName(pill.getPillName());
                userGetMypageFavoriteResponseDto.setPillItemSequence(f.getPillItemSequence());
                userGetMypageFavoriteResponseDto.setClassification(pill.getClassification());
                userGetMypageFavoriteResponseDto.setImage(pillImageRepository.findById(f.getPillItemSequence()).get().getImageLink());
                mypageFavoriteList.add(userGetMypageFavoriteResponseDto);
            }
        }

        UserGetMypageFavoriteResponse userGetMypageFavoriteResponse = new UserGetMypageFavoriteResponse();
        userGetMypageFavoriteResponse.setData(mypageFavoriteList);
        return userGetMypageFavoriteResponse;
    }
}
