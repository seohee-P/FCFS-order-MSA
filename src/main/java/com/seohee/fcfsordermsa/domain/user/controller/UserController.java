package com.seohee.fcfsordermsa.domain.user.controller;

import com.seohee.fcfsordermsa.domain.user.dto.UserResponseDto;
import com.seohee.fcfsordermsa.domain.user.dto.UserSignupRequestDto;
import com.seohee.fcfsordermsa.domain.user.service.UserService;
import com.seohee.fcfsordermsa.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ApiResponse<UserResponseDto> signupUser(@Valid @RequestBody UserSignupRequestDto requestDto, BindingResult bindingResult) {

        UserResponseDto responseDto = userService.signup(requestDto);

        return ApiResponse.ok(responseDto);
    }

}
