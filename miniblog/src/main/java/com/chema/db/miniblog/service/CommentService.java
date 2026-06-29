package com.chema.db.miniblog.service;

import com.chema.db.miniblog.model.User;
import com.chema.db.miniblog.repository.UserRepository;
import com.chema.db.miniblog.model.Post;
import com.chema.db.miniblog.repository.PostRepository;
import com.chema.db.miniblog.model.Comment;
import com.chema.db.miniblog.repository.CommentRepository;
import com.chema.db.miniblog.dto.CommentRequest;
import com.chema.db.miniblog.dto.CommentResponse;
import com.chema.db.miniblog.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final  CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(CommentMapper::toResponse)
                .toList();
    }

    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment", id));
        return CommentMapper.toResponse(comment);
    }

    public CommentResponse createComment(CommentRequest request) {
        Comment comment = CommentMapper.toEntity(request);
        Long postId = request.getPostId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Post", postId));

        Long authorId = request.getAutorId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", authorId));

        comment.setPost(post);
        comment.setAutor(author);

        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toResponse(savedComment);
    }

    public CommentResponse updateComment(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment", id));
        comment.setContent(request.getContent());
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toResponse(savedComment);
    }

    public void deleteComment(Long id) {commentRepository.deleteById(id);}
}
