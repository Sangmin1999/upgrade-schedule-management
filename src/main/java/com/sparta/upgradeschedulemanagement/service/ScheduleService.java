package com.sparta.upgradeschedulemanagement.service;

import com.sparta.upgradeschedulemanagement.scheduledto.*;
import com.sparta.upgradeschedulemanagement.entity.Schedule;
import com.sparta.upgradeschedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        Schedule newSchedule = new Schedule(requestDto);
        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                savedSchedule.getUserName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    public ScheduleDetailResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NullPointerException("일정이 없습니다"));

        return new ScheduleDetailResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NullPointerException("일정이 없습니다"));

        schedule.update(requestDto.getUserName(), requestDto.getTitle(), requestDto.getContent());

        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
