package com.sparta.upgradeschedulemanagement.domain.user.entity;

import com.sparta.upgradeschedulemanagement.domain.manager.entity.Manager;
import com.sparta.upgradeschedulemanagement.domain.common.entity.Timestamped;
import com.sparta.upgradeschedulemanagement.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated
    private UserRole userRole;

    public User(String username, String email, String password,UserRole userRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @OneToMany(mappedBy = "user")
    private List<Manager> managerList = new ArrayList<>();
}
