package com.sparta.upgradeschedulemanagement.commentdto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentSaveResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final String userName;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentSaveResponseDto(
            Long id,
            Long scheduleId,
            String userName,
            String content,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
