package com.sparta.upgradeschedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String content;

    public Comment(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
