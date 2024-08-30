package com.sparta.upgradeschedulemanagement.domain.schedule.service;

import com.sparta.upgradeschedulemanagement.domain.schedule.dto.request.ScheduleSaveRequestDto;
import com.sparta.upgradeschedulemanagement.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.sparta.upgradeschedulemanagement.domain.schedule.dto.response.ScheduleDetailResponseDto;
import com.sparta.upgradeschedulemanagement.domain.schedule.dto.response.SchedulePageResponseDto;
import com.sparta.upgradeschedulemanagement.domain.schedule.dto.response.ScheduleSaveResponseDto;
import com.sparta.upgradeschedulemanagement.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.sparta.upgradeschedulemanagement.domain.schedule.entity.Schedule;
import com.sparta.upgradeschedulemanagement.domain.schedule.repository.ScheduleRepository;
import com.sparta.upgradeschedulemanagement.domain.user.dto.UserDto;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import com.sparta.upgradeschedulemanagement.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleWeatherService scheduleWeatherService;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new NullPointerException("User not foound"));

        String todayWeather = scheduleWeatherService.getTodayWeather();

        Schedule newSchedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                todayWeather,
                user
        );
        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail()),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                todayWeather,
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    public ScheduleDetailResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NullPointerException("일정이 없습니다"));

        User user = schedule.getUser();

        return new ScheduleDetailResponseDto(
                schedule.getId(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail()),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWeather(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NullPointerException("일정이 없습니다"));

        schedule.update(requestDto.getTitle(), requestDto.getContent());


        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    public Page<SchedulePageResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);

        // Schedule 엔티티를 ScheduleResponseDto로 변환하기 위해 map 메서드 사용
        return schedules.map(schedule -> new SchedulePageResponseDto(
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCommentList().size(),
                        schedule.getWeather(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                )
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new NullPointerException("일정이 없습니다");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
