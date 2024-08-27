package com.sparta.upgradeschedulemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String title;
    private String content;

    public Schedule(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
