package com.example.repositories;

import com.example.entities.Comment;
import com.example.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Set<Comment> findByPost(Post post);
}
