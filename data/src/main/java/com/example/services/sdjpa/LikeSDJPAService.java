package com.example.services.sdjpa;

import com.example.entities.Chat;
import com.example.entities.Like;
import com.example.exceptions.NotFoundException;
import com.example.repositories.LikeRepository;
import com.example.services.LikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class LikeSDJPAService implements LikeService {
    private final LikeRepository likeRepository;

    @Override
    public Like findById(Long aLong) {
        return likeRepository.findById(aLong).orElseThrow(() -> {
            log.error("NotFoundException with chatId: {}", aLong);
            throw new NotFoundException("Chat with id");
        });
    }

    @Override
    public Set<Like> findAll() {
        log.info("class: LikeSDJPAService, invoke method: findAll()");
        Set<Like> likes = new HashSet<>();
        likeRepository.findAll().forEach(likes::add);
        return likes;
    }

    @Override
    public Like save(Like like) {
        log.info("class: LikeSDJPAService, invoke method: save(Like like), chatId - {}", like.getId());
        return likeRepository.save(like);
    }

    @Override
    public void delete(Like like) {
        log.info("class: LikeSDJPAService, invoke method: delete(Like likeId), likeId - {}", like.getId());
        likeRepository.delete(like);
    }

    @Override
    public void deleteById(Long likeId) {
        log.info("class: LikeSDJPAService, invoke method: deleteById(Long likeId), likeId - {}", likeId);
        likeRepository.deleteById(likeId);

    }
}
