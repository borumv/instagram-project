package com.example.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")

public class Profile implements  BaseEntity<Long>{

    @Builder
    public Profile(Long id, String nickName, String photo) {
        this.id = id;
        this.nickName = nickName;
        this.photo = photo;
    }

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickName;
    private String photo;

    public Profile() {

    }
}
