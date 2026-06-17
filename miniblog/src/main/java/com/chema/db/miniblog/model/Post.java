package com.chema.db.miniblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "title is required")
    private String title;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "content is required")
    private String content;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;
}
