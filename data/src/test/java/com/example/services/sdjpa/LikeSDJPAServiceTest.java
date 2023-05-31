package com.example.services.sdjpa;

import com.example.entities.Like;
import com.example.entities.Post;
import com.example.exceptions.LikeNotFoundException;
import com.example.exceptions.PostNotFoundException;
import com.example.repositories.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith({MockitoExtension.class})
class LikeSDJPAServiceTest {

    static Long LIKE_ID;
    Like like;

    Like like2;
    @Mock
    LikeRepository likeRepository;

    @InjectMocks
    LikeSDJPAService likeSDJPAService;

    @BeforeEach
    void setUp() {
        like = Like.builder().id(LIKE_ID).build();
        like2 = Like.builder().id(2L).build();
    }

    @Test
    void findById() {
        //given
        when(likeRepository.findById(LIKE_ID)).thenReturn(Optional.ofNullable(like));
        //when
        Like like = likeSDJPAService.findById(LIKE_ID);
        //then
        assertEquals(LIKE_ID, like.getId());
        verify(likeRepository, times(1)).findById(LIKE_ID);
    }

    @Test
    void findByIdThrowsMessageNotFoundException() {

        when(likeRepository.findById(LIKE_ID)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(LikeNotFoundException.class, () -> {
            likeSDJPAService.findById(LIKE_ID);
        });
        verify(likeRepository, times(1)).findById(LIKE_ID);
    }


    @Test
    void findAll() {
        when(likeRepository.findAll()).thenReturn(Set.of(like, like2));
        Set<Like> likes = likeSDJPAService.findAll();
        assertEquals(2,likes.size());
        verify(likeRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(likeRepository.save(like)).thenReturn(like);
        Like savedLike = likeSDJPAService.save(like);
        assertEquals(like, savedLike);
        verify(likeRepository, times(1)).save(like);
    }

    @Test
    void delete() {
        likeSDJPAService.delete(like);
        verify(likeRepository, times(1)).delete(like);
    }

    @Test
    void deleteById() {
        likeSDJPAService.deleteById(LIKE_ID);
        verify(likeRepository, times(1)).deleteById(LIKE_ID);
    }
}