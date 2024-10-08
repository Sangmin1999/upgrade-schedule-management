package com.sparta.upgradeschedulemanagement.domain.comment.repository;

import com.sparta.upgradeschedulemanagement.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
