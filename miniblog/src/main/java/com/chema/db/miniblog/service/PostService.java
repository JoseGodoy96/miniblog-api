package com.chema.db.miniblog.service;

import com.chema.db.miniblog.model.Post;
import com.chema.db.miniblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) { this.postRepository = postRepository; }

    public List<Post> getAllPosts() { return postRepository.findAll(); }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));
    }

    public Post createPost(Post post) { return postRepository.save(post); }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());

                    return postRepository.save(post);
                })
                .orElseThrow(() ->
                        new RuntimeException("Post not found"));
    }

    public void deletePost(Long id) { postRepository.deleteById(id); }
}
