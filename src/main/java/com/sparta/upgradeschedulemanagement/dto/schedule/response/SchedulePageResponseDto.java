package com.sparta.upgradeschedulemanagement.dto.schedule.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponseDto {

    private final String title;
    private final String content;
    private final int commentCount;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SchedulePageResponseDto(String title, String content, int commentCount,
                                   String userName, LocalDateTime createdAt, LocalDateTime modifiedAt)
    {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
