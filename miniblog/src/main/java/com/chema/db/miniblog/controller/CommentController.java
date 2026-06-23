package com.chema.db.miniblog.controller;

import com.chema.db.miniblog.model.Comment;
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
    public List<Comment> findAll() { return commentService.getAllComments(); }

    @GetMapping("/{id}")
    public Comment findOne(@PathVariable Long id) { return commentService.getCommentById(id); }

    @PostMapping
    public Comment create(@Valid @RequestBody Comment comment) { return commentService.createComment(comment); }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @Valid @RequestBody Comment comment) { return commentService.updateComment(id, comment); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { commentService.deleteComment(id); }
}
