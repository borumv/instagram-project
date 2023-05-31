package com.example.services;

import com.example.entities.Comment;
import com.example.entities.Like;
import com.example.entities.Post;

import java.util.Set;

public interface PostService extends CrudService<Post, Long>{
    Set<Comment> findAllCommentsByPostId(Long id);
    Set<Like> findAllLikesByPostId(Long id);
    Set<Post> findAllByUserId (Long userId);
}
