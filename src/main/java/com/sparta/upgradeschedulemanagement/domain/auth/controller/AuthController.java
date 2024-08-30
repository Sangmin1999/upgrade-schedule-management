package com.sparta.upgradeschedulemanagement.domain.auth.controller;

import com.sparta.upgradeschedulemanagement.domain.auth.dto.request.LoginRequetDto;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.request.SignupRequestDto;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.response.LoginResponseDto;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.response.SignupResponseDto;
import com.sparta.upgradeschedulemanagement.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return authService.signup(signupRequestDto);
    }

    @PostMapping("/auth/login")
    public LoginResponseDto login(@RequestBody LoginRequetDto loginRequetDto) {
        return authService.login(loginRequetDto);
    }
}
