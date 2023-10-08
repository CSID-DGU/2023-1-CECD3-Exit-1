package com.example.exitmedserver.user.controller;

import com.example.exitmedserver.user.dto.*;
import com.example.exitmedserver.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user/signup")
    public UserSignupResponseDto signup(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return userService.signup(userSignupRequestDto);
        } else {
            System.out.println("error");
        }
        System.out.println(userSignupRequestDto.getUserPassword());
        return userService.signup(userSignupRequestDto);
    }

    @PostMapping("/user/signup/check-duplicated")
    public UserCheckDuplicatedResponseDto checkDuplicated(@Valid @RequestBody UserCheckDuplicatedRequestDto userCheckDuplicatedRequestDto) {
        return userService.checkDuplicated(userCheckDuplicatedRequestDto);
    }

    @GetMapping("/auth/user/favorite")
    public UserGetMypageFavoriteResponse getMypageFavorite(@RequestHeader("Authorization") String jwtToken) {
        return userService.getMypageFavorite(jwtToken);
    }

    @GetMapping("/auth/test")
    public String testtest() {
        return "auth test Success";
    }
}
