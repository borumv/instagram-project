package com.example.services;

import com.example.entities.Post;
import com.example.entities.User;
import com.example.exceptions.UserNotFoundException;
import com.example.repositories.UserRepository;
import com.example.services.sdjpa.UserSDJPAService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    static Long USER_ID = 1L;

    private User user;
    private User user2;
    private User user3;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserSDJPAService userService;

    @BeforeEach
    public void beforeAll() {
        user = User.builder()
                .id(USER_ID)
                .firstName("Boris")
                .lastName("Vlasevsky")
                .email("boris@mail.com")
                .build();
        user2 = User.builder()
                .id(2L)
                .firstName("Vasya")
                .lastName("Pupkin")
                .email("vasya@mail.com")
                .build();
        user3= User.builder()
                .id(3L)
                .firstName("Jora")
                .lastName("Obzhorin")
                .email("jora@mail.com")
                .build();
        user.setFollowers(Set.of(user2, user3));
    }

    @Test
    void findById(){
        //given
        when(userRepository.findById(USER_ID)).thenReturn(Optional.ofNullable(user));
        //when
        User user = userService.findById(USER_ID);
        //then
        assertEquals(USER_ID, user.getId());
        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    void findByIdThrowsMessageNotFoundException() {

        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
        // Act & Assert
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.findById(USER_ID);
        });
        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    void findAllFollowersByUserId() {
        //given
        Set<User> followers = Set.of(user2, user3);
        when(userRepository.findFollowersByUserId(USER_ID)).thenReturn(followers);
        //when
        Set<User> userResponse = userService.findAllFollowersByUserId(USER_ID);
        //then
        assertEquals(2, userResponse.size());
    }

    @Test
    void findAllFollowingByUserId() {
        //given
        Set<User> followers = Set.of(user2, user3);
        when(userRepository.findFollowingByUserId(USER_ID)).thenReturn(followers);
        //when
        Set<User> userResponse = userService.findAllFollowingByUserId(USER_ID);
        //then
        assertEquals(2, userResponse.size());
    }

    @Test
    void findAll(){
        //given
        Set<User> allUsers = Set.of(user, user2, user3);
        when(userRepository.findAll()).thenReturn(allUsers);

        //when
        Set<User> userResponse = userService.findAll();

        //then
        assertEquals(3, userResponse.size());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    void delete(){
        userService.delete(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteThrowsNotFoundException() {
        User userNull = User.builder().build();
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.delete(userNull);
        });
         verifyNoInteractions(userRepository);
    }

    @Test
    void deleteById(){
        userService.deleteById(USER_ID);
        verify(userRepository, times(1)).deleteById(USER_ID);
    }




}