package com.sparta.upgradeschedulemanagement.controller;

import com.sparta.upgradeschedulemanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
}
