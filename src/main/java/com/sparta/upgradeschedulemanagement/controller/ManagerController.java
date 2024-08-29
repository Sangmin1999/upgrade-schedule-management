package com.sparta.upgradeschedulemanagement.controller;

import com.sparta.upgradeschedulemanagement.dto.manager.request.ManagerSaveRequestDto;
import com.sparta.upgradeschedulemanagement.dto.manager.response.ManagerDetailResponseDto;
import com.sparta.upgradeschedulemanagement.dto.manager.response.ManagerSaveResponseDto;
import com.sparta.upgradeschedulemanagement.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/schedules/{scheduleId}/managers")
    public ResponseEntity<ManagerSaveResponseDto> saveManager(@PathVariable Long scheduleId, @RequestBody ManagerSaveRequestDto managerSaveRequestDto) {
        return ResponseEntity.ok(managerService.saveManager(scheduleId,managerSaveRequestDto));
    }

    @GetMapping("/schedules/{scheduleId}/managers")
    public ResponseEntity<List<ManagerDetailResponseDto>> getManagers(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(managerService.getManagers(scheduleId));
    }


}
