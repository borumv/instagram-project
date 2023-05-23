package com.example.services.sdjpa;

import com.example.entities.Message;
import com.example.entities.Post;
import com.example.exceptions.NotFoundException;
import com.example.repositories.PostRepository;
import com.example.services.PostService;
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
    @Override
    public Post findById(Long messageId) {
        return postRepository.findById(messageId).orElseThrow(() -> {
            throw  new NotFoundException("Post with id");
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
}
