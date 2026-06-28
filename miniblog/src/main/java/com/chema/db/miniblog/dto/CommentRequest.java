package com.chema.db.miniblog.dto;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    @NotBlank(message = "content is required")
    private String content;

    @JoinColumn(name = "post_id")
    private Long postId;

    @JoinColumn(name = "autor_id")
    private Long autorId;
}
