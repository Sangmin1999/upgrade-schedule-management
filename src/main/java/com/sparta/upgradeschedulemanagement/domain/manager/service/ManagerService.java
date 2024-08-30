package com.sparta.upgradeschedulemanagement.domain.manager.service;

import com.sparta.upgradeschedulemanagement.domain.manager.dto.request.ManagerSaveRequestDto;
import com.sparta.upgradeschedulemanagement.domain.manager.dto.response.ManagerDetailResponseDto;
import com.sparta.upgradeschedulemanagement.domain.manager.dto.response.ManagerSaveResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.UserDto;
import com.sparta.upgradeschedulemanagement.domain.manager.entity.Manager;
import com.sparta.upgradeschedulemanagement.domain.schedule.entity.Schedule;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import com.sparta.upgradeschedulemanagement.domain.manager.repository.ManagerRepository;
import com.sparta.upgradeschedulemanagement.domain.schedule.repository.ScheduleRepository;
import com.sparta.upgradeschedulemanagement.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ManagerSaveResponseDto saveManager(Long scheduleId, ManagerSaveRequestDto managerSaveRequestDto) {
        // 실제로 있는 일정을 가져온다
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NullPointerException("Schedule not found."));

        // 등록하려고 하는 유저가 일정을 만든 사람인지 확인하면서 가져온다
        User user = userRepository.findById(managerSaveRequestDto.getScheduleUserId())
                .orElseThrow(() -> new NullPointerException("일정을 만든 유저가 아닙니다"));

        if (!(schedule.getUser() != null && ObjectUtils.nullSafeEquals(user.getId(), schedule.getUser().getId()))) {
            throw new NullPointerException("담당자를 등록하려고 하는 유저가 일정을 만든 유저가 아닙니다.");
        }

        // 등록하려고 하는 담당자가 실제로 존재하는 유저인지 확인하면서 가져온다
        User manager = userRepository.findById(managerSaveRequestDto.getScheduleUserId())
                .orElseThrow(() -> new NullPointerException("User not found."));

        Manager newManager = new Manager(manager, schedule);
        Manager savedManager = managerRepository.save(newManager);

        return new ManagerSaveResponseDto(savedManager.getId());
    }

    public List<ManagerDetailResponseDto> getManagers(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NullPointerException("Schedule not found."));

        List<Manager> managerList = managerRepository.findByScheduleId(scheduleId);

        List<ManagerDetailResponseDto> dtoList = new ArrayList<>();
        for (Manager manager : managerList) {
            User user = manager.getUser();
            dtoList.add(new ManagerDetailResponseDto(
                    manager.getId(),
                    new UserDto(user.getId(), user.getUsername(), user.getEmail())
            ));
        }
        return dtoList;
    }
}
