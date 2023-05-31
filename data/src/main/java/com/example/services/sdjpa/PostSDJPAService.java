package com.example.services.sdjpa;

import com.example.entities.Comment;
import com.example.entities.Like;
import com.example.entities.Post;
import com.example.entities.User;
import com.example.exceptions.PostNotFoundException;
import com.example.repositories.CommentRepository;
import com.example.repositories.LikeRepository;
import com.example.repositories.PostRepository;
import com.example.repositories.UserRepository;
import com.example.services.PostService;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Service
@Slf4j
public class PostSDJPAService implements PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> {
            throw  new PostNotFoundException(String.valueOf(postId));
        });
    }

    @Override
    public Set<Post> findAll() {
        Set<Post> posts = new HashSet<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Set<Comment> findAllCommentsByPostId(Long id) {
        Post post = findById(id);
        return commentRepository.findByPost(post);
    }
    @Override
    public Set<Like> findAllLikesByPostId(Long id) {
        Post post = findById(id);
        return likeRepository.findAllByPost(post);
    }

    @Override
    public Set<Post> findAllByUserId(Long userId) {
        User user = userService.findById(userId);
        return postRepository.findAllByUser(user);
    }
}
