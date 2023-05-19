package com.example.services.sdjpa;

import com.example.entities.Chat;
import com.example.entities.User;
import com.example.exceptions.NotFoundException;
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
            log.error("NotFoundException with chatId: {}", aLong);
            throw  new NotFoundException("Chat with id");
        });
    }

    @Override
    public Set<Chat> findAll() {
        log.info("class: ChatSDJPAService, invoke method: findAll()");
        Set<Chat> chats = new HashSet<>();
        chatRepository.findAll().forEach(chats::add);
        return chats;
    }

    @Override
    public Chat save(Chat chat) {
        log.info("class: ChatSDJPAService, invoke method: save(Chat chat), chatId - {}", chat.getId());
        return chatRepository.save(chat);
    }

    @Override
    public void delete(Chat chat) {
        log.info("class: ChatSDJPAService, invoke method: delete(Chat chat), chatId - {}", chat.getId());
        chatRepository.delete(chat);
    }

    @Override
    public void deleteById(Long aLong) {
        log.info("class: ChatSDJPAService, invoke method: deleteById(Long chatId), userId - {}", aLong);
        chatRepository.deleteById(aLong);
    }


}
