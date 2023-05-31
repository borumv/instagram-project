package com.example.services.sdjpa;

import com.example.entities.Comment;
import com.example.entities.Like;
import com.example.entities.Post;
import com.example.exceptions.CommentNotFoundEWxception;
import com.example.exceptions.LikeNotFoundException;
import com.example.repositories.CommentRepository;
import com.example.services.PostService;
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
class CommentSDJPAServiceTest {

    static Long COMMENT_ID;
    Comment comment;
    Comment comment2;
    @Mock
    CommentRepository commentRepository;
    @Mock
    PostService postService;

    @InjectMocks
    CommentSDJPAService commentSDJPAService;

    @BeforeEach
    void setUp() {
        comment = Comment.builder().id(COMMENT_ID).build();
        comment2 = Comment.builder().id(2L).build();
    }

    @Test
    void findById() {
        //given
        when(commentRepository.findById(COMMENT_ID)).thenReturn(Optional.ofNullable(comment));
        //when
        Comment like = commentSDJPAService.findById(COMMENT_ID);
        //then
        assertEquals(COMMENT_ID, like.getId());
        verify(commentRepository, times(1)).findById(COMMENT_ID);
    }

    @Test
    void findByIdThrowsMessageNotFoundException() {

        when(commentRepository.findById(COMMENT_ID)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(CommentNotFoundEWxception.class, () -> {
            commentSDJPAService.findById(COMMENT_ID);
        });
        verify(commentRepository, times(1)).findById(COMMENT_ID);
    }


    @Test
    void findAll() {
        when(commentRepository.findAll()).thenReturn(Set.of(comment, comment2));
        Set<Comment> comments = commentSDJPAService.findAll();
        assertEquals(2,comments.size());
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(commentRepository.save(comment)).thenReturn(comment);
        Comment savedLike = commentSDJPAService.save(comment);
        assertEquals(comment, savedLike);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void delete() {
        commentSDJPAService.delete(comment);
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void deleteById() {
        commentSDJPAService.deleteById(COMMENT_ID);
        verify(commentRepository, times(1)).deleteById(COMMENT_ID);
    }

    @Test
    void findParentsCommentsByPostId(){
        Long postId = 1L;
        Post post = Post.builder().id(postId).build();

        Comment parentComment1 = Comment.builder().id(1L).build();
        Comment parentComment2 =  Comment.builder().id(2L).build();
        Set<Comment> parentComments = Set.of(parentComment1, parentComment2);

        when(postService.findById(postId)).thenReturn(post);
        when(commentRepository.findAllByPostAndParentCommentIsNull(post)).thenReturn(parentComments);

        Set<Comment> result = commentSDJPAService.findParentsCommentsByPostId(postId);

        assertEquals(parentComments, result);
        verify(postService).findById(postId);
        verify(commentRepository).findAllByPostAndParentCommentIsNull(post);
    }


    @Test
    void findChildCommentsByParentId(){
        Long parentCommentId = 1L;

        Comment childComment1 = Comment.builder().id(3L).build();
        Comment childComment2 =  Comment.builder().id(2L).build();
        Set<Comment> childComments = Set.of(childComment1, childComment2);

        when(commentRepository.findAllByParentCommentId(parentCommentId)).thenReturn(childComments);

        Set<Comment> result = commentSDJPAService.findAllChildComments(parentCommentId);

        assertEquals(childComments, result);
        verify(commentRepository, times(1)).findAllByParentCommentId(parentCommentId);
    }
}