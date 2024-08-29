package com.sparta.upgradeschedulemanagement.dto.schedule.response;

import com.sparta.upgradeschedulemanagement.dto.user.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponseDto {

    private final String title;
    private final String content;
    private final int commentCount;
    private final UserDto user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SchedulePageResponseDto(String title, String content, int commentCount,
                                   UserDto user, LocalDateTime createdAt, LocalDateTime modifiedAt)
    {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
