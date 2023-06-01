package com.example.services.sdjpa;


import com.example.entities.User;
import com.example.exceptions.UserNotFoundException;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class UserSDJPAService implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElseThrow(() -> {
            throw  new UserNotFoundException(String.valueOf(aLong));
        });
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User save(User person) {
        return userRepository.save(person);
    }

    @Override
    public void delete(User user) {
        if(user.getId() == null){
            throw new UserNotFoundException("null");
        }
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public Set<User> findAllFollowersByUserId(Long userId) {
        return userRepository.findFollowersByUserId(userId);
    }

    @Override
    public Set<User> findAllFollowingByUserId(Long userId) {
        return userRepository.findFollowingByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )).orElseThrow(() -> new UserNotFoundException("failed to retrieve user: " + username));
    }
}
