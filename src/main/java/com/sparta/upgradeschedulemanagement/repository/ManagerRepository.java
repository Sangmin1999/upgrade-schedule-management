package com.sparta.upgradeschedulemanagement.repository;

import com.sparta.upgradeschedulemanagement.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
    List<Manager> findByScheduleId();
}
