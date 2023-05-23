package com.example.services.sdjpa;


import com.example.entities.Comment;
import com.example.entities.Message;
import com.example.exceptions.NotFoundException;
import com.example.repositories.MessageRepository;
import com.example.services.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class MessageSDJPAService implements MessageService {

    private final MessageRepository messageRepository;
    @Override
    public Message findById(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(() -> {
            throw  new NotFoundException("Comment with id");
        });
    }

    @Override
    public Set<Message> findAll() {
        Set<Message> chats = new HashSet<>();
        messageRepository.findAll().forEach(chats::add);
        return chats;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public void deleteById(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
