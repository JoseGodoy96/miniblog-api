package com.chema.db.miniblog.service;

import com.chema.db.miniblog.exception.ResourceNotFoundException;
import com.chema.db.miniblog.model.Comment;
import com.chema.db.miniblog.model.Post;
import com.chema.db.miniblog.model.User;
import com.chema.db.miniblog.repository.CommentRepository;
import com.chema.db.miniblog.repository.UserRepository;
import com.chema.db.miniblog.repository.PostRepository;
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

    public List<Comment> getAllComments() { return commentRepository.findAll(); }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment", id));
    }

    public Comment createComment(Comment comment) {
        Long postId = comment.getPost().getId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Post", postId));

        Long authorId = comment.getAutor().getId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", authorId));

        comment.setPost(post);
        comment.setAutor(author);

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setContent(updatedComment.getContent());

                    return commentRepository.save(comment);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment", id));
    }

    public void deleteComment(Long id) {commentRepository.deleteById(id);}
}
