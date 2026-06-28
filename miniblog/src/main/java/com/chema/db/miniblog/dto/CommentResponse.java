package com.chema.db.miniblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {

    private Long id;
    private String content;
    private Long autorId;
    private String autorUsername;
    private Long postId;
    private String postTitle;
}
