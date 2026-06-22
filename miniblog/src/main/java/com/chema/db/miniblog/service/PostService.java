package com.chema.db.miniblog.service;

import com.chema.db.miniblog.exception.ResourceNotFoundException;
import com.chema.db.miniblog.model.Post;
import com.chema.db.miniblog.model.User;
import com.chema.db.miniblog.repository.UserRepository;
import com.chema.db.miniblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,  UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() { return postRepository.findAll(); }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", id));
    }

    public Post createPost(Post post) {
        Long authorId = post.getAutor().getId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", authorId));

        post.setAutor(author);

        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());

                    return postRepository.save(post);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", id));
    }

    public void deletePost(Long id) { postRepository.deleteById(id); }
}
