package com.example.repositories;

import com.example.entities.Like;
import com.example.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LikeRepository extends CrudRepository<Like, Long> {
    Set<Like> findAllByPost(Post post);
}
