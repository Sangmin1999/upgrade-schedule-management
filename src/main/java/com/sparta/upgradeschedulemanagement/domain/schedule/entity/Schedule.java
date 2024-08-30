package com.sparta.upgradeschedulemanagement.domain.schedule.entity;

import com.sparta.upgradeschedulemanagement.domain.comment.entity.Comment;
import com.sparta.upgradeschedulemanagement.domain.common.entity.Timestamped;
import com.sparta.upgradeschedulemanagement.domain.manager.entity.Manager;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;

    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<Manager> managerList = new ArrayList<>();
}
