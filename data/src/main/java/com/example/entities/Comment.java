package com.example.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "comments")
public class Comment implements BaseEntity<Long>, Seenable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_text")
    private String message;

    @Column(name = "date_of_comment")
    private Timestamp dateOfComment;

    @Column(name = "seen_status")
    boolean seen;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    Comment parentComment;

    @OneToMany(mappedBy = "parentComment",  cascade = CascadeType.ALL)
    Set<Comment> comments;

}
