package com.example.repositories;

import com.example.entities.Post;
import com.example.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PostRepository extends CrudRepository<Post,Long> {
    Set<Post> findAllByUser(User user);
}
