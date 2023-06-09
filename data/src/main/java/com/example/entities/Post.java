package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
@Builder
public class Post implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String message;

    private String content;

    @Column(name = "date_of_create")
    private Timestamp dateOfCreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "post")
    private Set<Like> likes;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

}
