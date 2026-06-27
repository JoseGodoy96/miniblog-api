package com.chema.db.miniblog.service;

import com.chema.db.miniblog.model.User;
import com.chema.db.miniblog.repository.UserRepository;
import com.chema.db.miniblog.model.Post;
import com.chema.db.miniblog.repository.PostRepository;
import com.chema.db.miniblog.dto.PostRequest;
import com.chema.db.miniblog.dto.PostResponse;
import com.chema.db.miniblog.exception.ResourceNotFoundException;
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

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toResponse)
                .toList();
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", id));

        return PostMapper.toResponse(post);
    }

    public PostResponse createPost(PostRequest request) {
        Post post = PostMapper.toEntity(request);
        Long authorId = request.getAutorId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", authorId));

        post.setAutor(author);

        Post savedPost = postRepository.save(post);
        return PostMapper.toResponse(savedPost);
    }

    public PostResponse updatePost(Long id, PostRequest request) {

        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", id));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        Post updatedPost = postRepository.save(post);

        return PostMapper.toResponse(updatedPost);
    }

    public void deletePost(Long id) { postRepository.deleteById(id); }
}
