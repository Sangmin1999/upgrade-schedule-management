package com.sparta.upgradeschedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String content;

    public Comment(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public void saveSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
