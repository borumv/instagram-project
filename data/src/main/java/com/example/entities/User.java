package com.example.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")

public class User implements BaseEntity<Long> {

    @Builder
    public User(Long id, String firstName, String lastName, String email, String password, Timestamp dateOfRegistry, Timestamp dateOfLastIn, Profile profile, List<Post> posts, Set<User> followers, Set<User> following, List<Chat> chats, Set<Post> seenPosts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfRegistry = dateOfRegistry;
        this.dateOfLastIn = dateOfLastIn;
        this.profile = profile;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
        this.chats = chats;
        this.seenPosts = seenPosts;
    }

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "followers",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "friend_id"))
    Set<User> followers;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    Set<User> following;

    // Остальные поля пользователя
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Chat> chats = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "posts_seen",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> seenPosts;


    public User() {

    }
}
