package com.sparta.upgradeschedulemanagement.domain.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    private Long userId;
    private String content;
}
