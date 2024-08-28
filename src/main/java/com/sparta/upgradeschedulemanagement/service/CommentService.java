package com.sparta.upgradeschedulemanagement.service;

import com.sparta.upgradeschedulemanagement.dto.comment.request.CommentSaveRequestDto;
import com.sparta.upgradeschedulemanagement.dto.comment.request.CommentUpdateRequestDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentDetailResponseDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentSaveResponseDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentSimpleResponseDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentUpdateResponseDto;
import com.sparta.upgradeschedulemanagement.entity.Comment;
import com.sparta.upgradeschedulemanagement.entity.Schedule;
import com.sparta.upgradeschedulemanagement.repository.CommentRepository;
import com.sparta.upgradeschedulemanagement.repository.ScheduleRepository;
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

    @Transactional
    public CommentSaveResponseDto saveComment(Long scheduleId, CommentSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NullPointerException("스케줄이 없습니다"));
        Comment newComment = new Comment(requestDto.getUserName(), requestDto.getContent());
        newComment.saveSchedule(schedule);
        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(
                savedComment.getId(),
                schedule.getId(),
                savedComment.getUserName(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    public List<CommentSimpleResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentSimpleResponseDto> dtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentSimpleResponseDto dto = new CommentSimpleResponseDto(
                    comment.getId(),
                    comment.getSchedule().getId(),
                    comment.getUserName(),
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

        return new CommentDetailResponseDto(
                comment.getId(),
                comment.getSchedule().getId(),
                comment.getUserName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다"));

        comment.updateComment(requestDto.getContent());

        return new CommentUpdateResponseDto(
                comment.getId(),
                comment.getSchedule().getId(),
                comment.getUserName(),
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
