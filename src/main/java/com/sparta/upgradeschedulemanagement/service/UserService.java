package com.sparta.upgradeschedulemanagement.service;

import com.sparta.upgradeschedulemanagement.dto.user.request.UserSaveRequestDto;
import com.sparta.upgradeschedulemanagement.dto.user.response.UserDetailResponseDto;
import com.sparta.upgradeschedulemanagement.dto.user.response.UserSaveResponseDto;
import com.sparta.upgradeschedulemanagement.dto.user.response.UserSimpleResponseDto;
import com.sparta.upgradeschedulemanagement.entity.User;
import com.sparta.upgradeschedulemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto requestDto) {

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }
        User newUser = new User(requestDto.getUserName(), requestDto.getEmail());
        User savedUser = userRepository.save(newUser);

        return new UserSaveResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    public List<UserSimpleResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();

        List<UserSimpleResponseDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            UserSimpleResponseDto dto = new UserSimpleResponseDto(user.getId(), user.getUsername());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public UserDetailResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("User not found."));
        return new UserDetailResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NullPointerException("User not found.");
        }

        userRepository.deleteById(userId);
    }
}
