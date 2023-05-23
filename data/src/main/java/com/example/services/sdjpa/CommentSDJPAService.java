package com.example.services.sdjpa;

import com.example.entities.Chat;
import com.example.entities.Comment;
import com.example.exceptions.NotFoundException;
import com.example.repositories.ChatRepository;
import com.example.repositories.CommentRepository;
import com.example.services.CommentService;
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
    @Override
    public Comment findById(Long aLong) {
        return commentRepository.findById(aLong).orElseThrow(() -> {
            throw  new NotFoundException("Comment with id");
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

}
