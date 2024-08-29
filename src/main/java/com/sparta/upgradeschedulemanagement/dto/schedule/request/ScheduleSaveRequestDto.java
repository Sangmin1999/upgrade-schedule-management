package com.sparta.upgradeschedulemanagement.dto.schedule.request;

import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    private Long userId;
    private String title;
    private String content;
}
