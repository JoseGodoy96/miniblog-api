package com.chema.db.miniblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    @NotBlank(message = "content is required")
    private String content;

    @NotNull(message = "postId is required")
    private Long postId;

    @NotNull(message = "autorId is required")
    private Long autorId;
}
