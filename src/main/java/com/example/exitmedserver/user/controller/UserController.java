package com.example.exitmedserver.user.controller;

import com.example.exitmedserver.user.dto.UserCheckDuplicatedRequestDto;
import com.example.exitmedserver.user.dto.UserCheckDuplicatedResponseDto;
import com.example.exitmedserver.user.dto.UserSignupRequestDto;
import com.example.exitmedserver.user.dto.UserSignupResponseDto;
import com.example.exitmedserver.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public UserCheckDuplicatedResponseDto checkDuplicated(@Valid @RequestBody UserCheckDuplicatedRequestDto userCheckDuplicatedRequestDto, BindingResult bindingResult) {
        return userService.checkDuplicated(userCheckDuplicatedRequestDto);
    }

    @GetMapping("/auth/test")
    public String testtest() {
        return "auth test Success";
    }
}
