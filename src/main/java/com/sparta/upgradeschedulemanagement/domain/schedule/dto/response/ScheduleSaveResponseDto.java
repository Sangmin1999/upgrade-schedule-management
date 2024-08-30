package com.sparta.upgradeschedulemanagement.domain.schedule.dto.response;

import com.sparta.upgradeschedulemanagement.domain.user.dto.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {

    private final Long id;
    private final UserDto user;
    private final String title;
    private final String content;
    private final String weather;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleSaveResponseDto(
            Long id,
            UserDto user,
            String title,
            String content, String weather,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.weather = weather;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
