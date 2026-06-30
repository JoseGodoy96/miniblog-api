package com.chema.db.miniblog.controller;

import com.chema.db.miniblog.dto.CommentRequest;
import com.chema.db.miniblog.dto.CommentResponse;
import com.chema.db.miniblog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @GetMapping
    public List<CommentResponse> findAll() { return commentService.getAllComments(); }

    @GetMapping("/{id}")
    public CommentResponse findOne(@PathVariable Long id) { return commentService.getCommentById(id); }

    @PostMapping
    public CommentResponse create(@Valid @RequestBody CommentRequest request) { return commentService.createComment(request); }

    @PutMapping("/{id}")
    public CommentResponse update(@PathVariable Long id, @Valid @RequestBody CommentRequest request) { return commentService.updateComment(id, request); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { commentService.deleteComment(id); }
}
