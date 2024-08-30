package com.sparta.upgradeschedulemanagement.domain.user.controller;

import com.sparta.upgradeschedulemanagement.domain.user.dto.request.UserSaveRequestDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.response.UserDetailResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.response.UserSaveResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.response.UserSimpleResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserSaveResponseDto> saveUser(@RequestBody UserSaveRequestDto requestDto) {
        return ResponseEntity.ok(userService.saveUser(requestDto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserSimpleResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDetailResponseDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
