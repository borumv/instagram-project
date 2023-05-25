package com.example.services.sdjpa;

import com.example.entities.Chat;
import com.example.entities.User;
import com.example.exceptions.ChatNotFoundException;
import com.example.repositories.ChatRepository;
import com.example.services.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class ChatSDJPAService implements ChatService {

    private final ChatRepository chatRepository;
    @Override
    public Chat findById(Long aLong) {
        return chatRepository.findById(aLong).orElseThrow(() -> {
            throw  new ChatNotFoundException(String.valueOf(aLong));
        });
    }

    @Override
    public Set<Chat> findAll() {
        Set<Chat> chats = new HashSet<>();
        chatRepository.findAll().forEach(chats::add);
        return chats;
    }

    @Override
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public void delete(Chat chat) {
        chatRepository.delete(chat);
    }

    @Override
    public void deleteById(Long aLong) {
        chatRepository.deleteById(aLong);
    }

}
