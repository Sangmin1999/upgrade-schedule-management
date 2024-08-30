package com.sparta.upgradeschedulemanagement.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserSimpleResponseDto {

    private final Long id;
    private final String userName;

    public UserSimpleResponseDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
