package com.sparta.upgradeschedulemanagement.service;

import com.sparta.upgradeschedulemanagement.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
}
