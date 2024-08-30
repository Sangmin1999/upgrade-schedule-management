package com.sparta.upgradeschedulemanagement.domain.schedule.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponseDto {

    private final String title;
    private final String content;
    private final int commentCount;
    private final String weather;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SchedulePageResponseDto(String title, String content, int commentCount, String weather,
                                   LocalDateTime createdAt, LocalDateTime modifiedAt)
    {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.weather = weather;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
