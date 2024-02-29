package com.ngleanhvu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id", nullable = false)
    private Post post;
}
