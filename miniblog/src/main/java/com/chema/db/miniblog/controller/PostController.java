package com.chema.db.miniblog.controller;

import com.chema.db.miniblog.model.Post;
import com.chema.db.miniblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {this.postService = postService;}

    @GetMapping
    public List<Post> findAll() { return postService.getAllPosts(); }

    @GetMapping("/{id}")
    public Post findOne(@PathVariable Long id) { return postService.getPostById(id); }

    @PostMapping
    public Post create(@RequestBody Post post) { return postService.createPost(post); }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) { return postService.updatePost(id, post); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {postService.deletePost(id);}

}
