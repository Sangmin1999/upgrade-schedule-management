package com.sparta.upgradeschedulemanagement.entity;

import com.sparta.upgradeschedulemanagement.dto.schedule.request.ScheduleSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    public Schedule(ScheduleSaveRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList = new ArrayList<>();

}
