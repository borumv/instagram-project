package com.example.services;

import com.example.entities.Comment;

import java.util.Set;

public interface CommentService extends CrudService<Comment,Long>{
   Set<Comment> findParentsCommentsByPostId(Long postId);
   Set<Comment> findAllChildComments(Long commentId);
}
