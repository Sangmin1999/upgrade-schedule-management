package com.sparta.upgradeschedulemanagement.domain.manager.entity;

import com.sparta.upgradeschedulemanagement.domain.schedule.entity.Schedule;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Manager(User user, Schedule schedule) {
        this.user = user;
        this.schedule = schedule;
    }
}
