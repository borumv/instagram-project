package com.example.services.sdjpa;

import com.example.entities.Comment;
import com.example.entities.Like;
import com.example.entities.Post;
import com.example.entities.User;
import com.example.exceptions.PostNotFoundException;
import com.example.repositories.CommentRepository;
import com.example.repositories.LikeRepository;
import com.example.repositories.PostRepository;
import com.example.services.UserService;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith({MockitoExtension.class})
class PostSDJPAServiceTest {


    static Long LIKE_ID = 1L;
    User user;
    User user2;
    User user3;

    Post post;
    Post post2;

    @Mock
    PostRepository postRepository;
    @Mock
    LikeRepository likeRepository;
    @Mock
    CommentRepository commentRepository;

    @Mock
    UserService userService;

    @InjectMocks
    PostSDJPAService postService;

    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1L)
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
        post = Post.builder()
                .id(LIKE_ID)
                .content("content.jpg")
                .user(user)
                .build();
        post2 = Post.builder()
                .id(2L)
                .build();

    }

    @Test
    void findById() {
        //given
        when(postRepository.findById(LIKE_ID)).thenReturn(Optional.ofNullable(post));
        //when
        Post post = postService.findById(LIKE_ID);
        //then
        assertEquals(LIKE_ID, post.getId());
        verify(postRepository, times(1)).findById(LIKE_ID);
    }

    @Test
    void findByIdThrowsMessageNotFoundException() {

        when(postRepository.findById(LIKE_ID)).thenReturn(Optional.empty());
        // Act & Assert
        Assertions.assertThrows(PostNotFoundException.class, () -> {
            postService.findById(LIKE_ID);
        });
        verify(postRepository, times(1)).findById(LIKE_ID);
    }


    @Test
    void findAll() {
        when(postRepository.findAll()).thenReturn(Set.of(post, post2));
        Set<Post> posts = postService.findAll();
        assertEquals(2,posts.size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.save(post);
        assertEquals(post, savedPost);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void delete() {
        postService.delete(post);
        verify(postRepository, times(1)).delete(post);
    }

    @Test
    void deleteById() {
        postService.deleteById(LIKE_ID);
        verify(postRepository, times(1)).deleteById(LIKE_ID);
    }

    @Test
    void findAllCommentsByPostId() {

        Comment comment1 = Comment.builder().id(1L).post(post).build();
        Comment comment2 = Comment.builder().id(2L).post(post).build();
        Set<Comment> comments = Set.of(comment1, comment2);

        when(postRepository.findById(LIKE_ID)).thenReturn(Optional.of(post));
        when(commentRepository.findByPost(post)).thenReturn(comments);

        Set<Comment> result = postService.findAllCommentsByPostId(LIKE_ID);

        assertEquals(comments, result);
        verify(postRepository).findById(LIKE_ID);
        verify(commentRepository).findByPost(post);
    }

    @Test
    void findAllLikesByPostId() {
        Post post = new Post();
        post.setId(LIKE_ID);

        Like like1 = new Like();
        Like like2 = new Like();
        Set<Like> likes = Set.of(like1, like2);

        when(postRepository.findById(LIKE_ID)).thenReturn(Optional.of(post));
        when(likeRepository.findAllByPost(post)).thenReturn(likes);

        Set<Like> result = postService.findAllLikesByPostId(LIKE_ID);

        assertEquals(likes, result);
        verify(postRepository, times(1)).findById(LIKE_ID);
        verify(likeRepository, times(1)).findAllByPost(post);
    }

    @Test
    void  findAllByUserId() {
        long userId = 1L;
        User exUser = User.builder().id(userId).build();
        Set<Post> posts = Set.of(Post.builder().id(2L).build(), Post.builder().id(2L).build());
        when(userService.findById(userId)).thenReturn(exUser);
        when(postRepository.findAllByUser(exUser)).thenReturn(posts);
        Set<Post> result = postService.findAllByUserId(1L);

        assertEquals(posts, result);
        verify(userService, times(1)).findById(userId);
        verify(postRepository, times(1)).findAllByUser(exUser);

    }
}