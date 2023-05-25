package com.example.services.sdjpa;

import com.example.entities.Like;
import com.example.exceptions.LikeNotFoundException;
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
            throw new LikeNotFoundException(String.valueOf(aLong));
        });
    }

    @Override
    public Set<Like> findAll() {
        Set<Like> likes = new HashSet<>();
        likeRepository.findAll().forEach(likes::add);
        return likes;
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public void delete(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public void deleteById(Long likeId) {
        likeRepository.deleteById(likeId);

    }
}
