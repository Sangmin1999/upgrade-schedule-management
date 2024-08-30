package com.sparta.upgradeschedulemanagement.domain.user.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveResponseDto {

    private final String bearerToken;

    public UserSaveResponseDto(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
