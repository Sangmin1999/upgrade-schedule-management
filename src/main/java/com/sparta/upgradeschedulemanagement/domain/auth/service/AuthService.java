package com.sparta.upgradeschedulemanagement.domain.auth.service;

import com.sparta.upgradeschedulemanagement.config.JwtUtil;
import com.sparta.upgradeschedulemanagement.config.PasswordEncoder;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.request.LoginRequetDto;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.request.SignupRequestDto;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.response.LoginResponseDto;
import com.sparta.upgradeschedulemanagement.domain.auth.dto.response.SignupResponseDto;
import com.sparta.upgradeschedulemanagement.domain.auth.exception.AuthException;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import com.sparta.upgradeschedulemanagement.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }

        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        User newUser = new User(signupRequestDto.getUserName(), signupRequestDto.getEmail(),encodedPassword);
        User savedUser = userRepository.save(newUser);

        String bearerToken = jwtUtil.createToken(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );

        return new SignupResponseDto(bearerToken);
    }

    public LoginResponseDto login(LoginRequetDto loginRequetDto) {
        User user = userRepository.findByEmail(loginRequetDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException(("가입되지 않은 유저입니다."))
        );

        // 로그인 시 이메일과 비밀번호가 일치하지 않는 경우 401을 반환합니다.
        if (!passwordEncoder.matches((loginRequetDto.getPassword()), user.getPassword())) {
            throw new AuthException("잘못된 비밀번호입니다.");
        }

        String bearerToken = jwtUtil.createToken(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );

        return new LoginResponseDto((bearerToken));
    }
}
