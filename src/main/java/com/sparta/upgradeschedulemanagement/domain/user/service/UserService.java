package com.sparta.upgradeschedulemanagement.domain.user.service;

import com.sparta.upgradeschedulemanagement.config.JwtUtil;
import com.sparta.upgradeschedulemanagement.config.PasswordEncoder;
import com.sparta.upgradeschedulemanagement.domain.user.dto.request.UserSaveRequestDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.response.UserDetailResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.response.UserSaveResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.dto.response.UserSimpleResponseDto;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import com.sparta.upgradeschedulemanagement.domain.user.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto requestDto) {

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User newUser = new User(requestDto.getUserName(), requestDto.getEmail(),encodedPassword);
        User savedUser = userRepository.save(newUser);

        String bearerToken = jwtUtil.createToken(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );

        return new UserSaveResponseDto(bearerToken);

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
