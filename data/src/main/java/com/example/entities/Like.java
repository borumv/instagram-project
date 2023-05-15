package com.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like implements BaseEntity<Long>, Seenable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column(name = "seen_status")
    private boolean seen;

    @Column(name = "date_of_like")
    private Timestamp dateOfLike;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id")
    User user;



}
