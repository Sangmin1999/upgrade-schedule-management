package com.sparta.upgradeschedulemanagement.controller;

import com.sparta.upgradeschedulemanagement.dto.comment.request.CommentSaveRequestDto;
import com.sparta.upgradeschedulemanagement.dto.comment.request.CommentUpdateRequestDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentDetailResponseDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentSaveResponseDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentSimpleResponseDto;
import com.sparta.upgradeschedulemanagement.dto.comment.response.CommentUpdateResponseDto;
import com.sparta.upgradeschedulemanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(@PathVariable Long scheduleId, @RequestBody CommentSaveRequestDto requestDto) {
        return ResponseEntity.ok(commentService.saveComment(scheduleId, requestDto));
    }

    @GetMapping("/schedules/comments")
    public ResponseEntity<List<CommentSimpleResponseDto>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDetailResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, requestDto));
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
