package com.chema.db.miniblog.service;

import com.chema.db.miniblog.dto.CommentRequest;
import com.chema.db.miniblog.dto.CommentResponse;
import com.chema.db.miniblog.model.Comment;

public class CommentMapper {
    public static Comment toEntity(CommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        return comment;
    }

    public static CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
        response.setAutorId(comment.getAutor().getId());
        response.setAutorUsername(comment.getAutor().getUsername());
        response.setPostId(comment.getPost().getId());
        response.setPostTitle(comment.getPost().getTitle());
        return response;
    }
}
