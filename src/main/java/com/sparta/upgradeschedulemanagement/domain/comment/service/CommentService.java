package com.sparta.upgradeschedulemanagement.domain.comment.service;

import com.sparta.upgradeschedulemanagement.domain.comment.dto.request.CommentSaveRequestDto;
import com.sparta.upgradeschedulemanagement.domain.comment.dto.request.CommentUpdateRequestDto;
import com.sparta.upgradeschedulemanagement.domain.comment.dto.response.CommentDetailResponseDto;
import com.sparta.upgradeschedulemanagement.domain.comment.dto.response.CommentSaveResponseDto;
import com.sparta.upgradeschedulemanagement.domain.comment.dto.response.CommentSimpleResponseDto;
import com.sparta.upgradeschedulemanagement.domain.comment.dto.response.CommentUpdateResponseDto;
import com.sparta.upgradeschedulemanagement.dto.user.UserDto;
import com.sparta.upgradeschedulemanagement.domain.comment.entity.Comment;
import com.sparta.upgradeschedulemanagement.domain.schedule.entity.Schedule;
import com.sparta.upgradeschedulemanagement.domain.user.entity.User;
import com.sparta.upgradeschedulemanagement.domain.comment.repository.CommentRepository;
import com.sparta.upgradeschedulemanagement.domain.schedule.repository.ScheduleRepository;
import com.sparta.upgradeschedulemanagement.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long scheduleId, CommentSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NullPointerException("스케줄이 없습니다"));

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new NullPointerException("User not foound."));
        Comment newComment = new Comment(requestDto.getContent(),user);
        newComment.saveSchedule(schedule);
        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(
                savedComment.getId(),
                schedule.getId(),
                new UserDto(user.getId(),user.getUsername(),user.getEmail()),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    public List<CommentSimpleResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentSimpleResponseDto> dtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            User user = comment.getUser();
            CommentSimpleResponseDto dto = new CommentSimpleResponseDto(
                    comment.getId(),
                    comment.getSchedule().getId(),
                    new UserDto(user.getId(),user.getUsername(),user.getEmail()),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CommentDetailResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다"));

        User user = comment.getUser();
        return new CommentDetailResponseDto(
                comment.getId(),
                comment.getSchedule().getId(),
                new UserDto(user.getId(),user.getUsername(),user.getEmail()),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다"));

        User user = comment.getUser();

        comment.updateComment(requestDto.getContent());

        return new CommentUpdateResponseDto(
                comment.getId(),
                comment.getSchedule().getId(),
                new UserDto(user.getId(),user.getUsername(),user.getEmail()),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다"));

        commentRepository.delete(comment);
    }
}
