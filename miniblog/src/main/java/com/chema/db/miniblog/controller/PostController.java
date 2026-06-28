package com.chema.db.miniblog.controller;

import com.chema.db.miniblog.dto.PostRequest;
import com.chema.db.miniblog.dto.PostResponse;
import com.chema.db.miniblog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> findAll() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponse findOne(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostResponse create(@Valid @RequestBody PostRequest request) {
        return postService.createPost(request);
    }

    @PutMapping("/{id}")
    public PostResponse update(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
