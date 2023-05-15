package com.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements BaseEntity<Long> {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "date_of_registry")
    private Timestamp dateOfRegistry;

    @Column(name = "date_of_last_in")
    private Timestamp dateOfLastIn;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    @ManyToMany
    @JoinTable(name = "followers",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "friend_id"))
    Set<User> followers;

    @ManyToMany(mappedBy = "followers")
    Set<User> following;

    // Остальные поля пользователя
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "posts_seen",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> seenPosts;


}
