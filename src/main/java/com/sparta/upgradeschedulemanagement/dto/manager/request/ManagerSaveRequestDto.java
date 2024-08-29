package com.sparta.upgradeschedulemanagement.dto.manager.request;

import lombok.Getter;

@Getter
public class ManagerSaveRequestDto {

    private Long scheduleUserId; // 일정 작성자 유저 id
    private Long managerUserId; // 일정 작성자가 배치하는 유저 id
}
