package com.sparta.upgradeschedulemanagement.controller;

import com.sparta.upgradeschedulemanagement.dto.schedule.request.ScheduleSaveRequestDto;
import com.sparta.upgradeschedulemanagement.dto.schedule.request.ScheduleUpdateRequestDto;
import com.sparta.upgradeschedulemanagement.dto.schedule.response.ScheduleDetailResponseDto;
import com.sparta.upgradeschedulemanagement.dto.schedule.response.SchedulePageResponseDto;
import com.sparta.upgradeschedulemanagement.dto.schedule.response.ScheduleSaveResponseDto;
import com.sparta.upgradeschedulemanagement.dto.schedule.response.ScheduleUpdateResponseDto;
import com.sparta.upgradeschedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(@RequestBody ScheduleSaveRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(requestDto));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleDetailResponseDto> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId,
                                                                    @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, requestDto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<Page<SchedulePageResponseDto>> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // 수정일 기준으로 내림차순 정렬된 페이지네이션 요청을 생성
        return ResponseEntity.ok(scheduleService.getSchedules(page, size));
    }
}
