package com.example.services.sdjpa;

import com.example.entities.Chat;
import com.example.entities.Comment;

import com.example.entities.Post;
import com.example.exceptions.CommentNotFoundEWxception;
import com.example.repositories.ChatRepository;
import com.example.repositories.CommentRepository;
import com.example.services.CommentService;
import com.example.services.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Service
@Slf4j
public class CommentSDJPAService implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    @Override
    public Comment findById(Long aLong) {
        return commentRepository.findById(aLong).orElseThrow(() -> {
            throw  new CommentNotFoundEWxception(String.valueOf(aLong));
        });
    }

    @Override
    public Set<Comment> findAll() {
        Set<Comment> chats = new HashSet<>();
        commentRepository.findAll().forEach(chats::add);
        return chats;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteById(Long aLong) {
        commentRepository.deleteById(aLong);
    }

    @Override
    public Set<Comment> findParentsCommentsByPostId(Long postId) {
        Post post = postService.findById(postId);
        return commentRepository.findAllByPostAndParentCommentIsNull(post);
    }

    @Override
    public Set<Comment> findAllChildComments(Long commentId) {

        return commentRepository.findAllByParentCommentId(commentId);
    }
}
