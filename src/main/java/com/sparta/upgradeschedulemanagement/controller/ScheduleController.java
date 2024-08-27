package com.sparta.upgradeschedulemanagement.controller;

import com.sparta.upgradeschedulemanagement.dto.*;
import com.sparta.upgradeschedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
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
}
