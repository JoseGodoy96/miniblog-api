package com.chema.db.miniblog.service;

import com.chema.db.miniblog.dto.PostRequest;
import com.chema.db.miniblog.dto.PostResponse;
import com.chema.db.miniblog.model.Post;

public class PostMapper {
    public static Post toEntity(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        return post;
    }

    public static PostResponse toResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setAutorId(post.getAutor().getId());
        response.setAutorUsername(post.getAutor().getUsername());
        return response;
    }
}
