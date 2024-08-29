package com.sparta.upgradeschedulemanagement.dto.comment.response;

import com.sparta.upgradeschedulemanagement.dto.user.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentUpdateResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final UserDto user;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentUpdateResponseDto(
            Long id,
            Long scheduleId,
            UserDto user,
            String content,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
