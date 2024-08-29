package com.sparta.upgradeschedulemanagement.dto.schedule.response;

import com.sparta.upgradeschedulemanagement.dto.user.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleUpdateResponseDto(
            Long id,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
