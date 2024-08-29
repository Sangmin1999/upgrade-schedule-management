package com.sparta.upgradeschedulemanagement.dto.schedule.response;

import com.sparta.upgradeschedulemanagement.dto.user.UserDto;
import com.sparta.upgradeschedulemanagement.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDetailResponseDto {

    private final Long id;
    private final UserDto user;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleDetailResponseDto(
            Long id,
            UserDto user,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
