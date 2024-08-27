package com.sparta.upgradeschedulemanagement.repository;

import com.sparta.upgradeschedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
