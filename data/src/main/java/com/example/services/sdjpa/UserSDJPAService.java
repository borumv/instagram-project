package com.example.services.sdjpa;


import com.example.entities.User;
import com.example.exceptions.NotFoundException;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class UserSDJPAService implements UserService {
    private final UserRepository userRepository;
    @Override
    public User findById(Long aLong) {
        log.info("class: UserSDJPAService, invoke method: findById(),  id: {}", aLong);
        return userRepository.findById(aLong).orElseThrow(() -> {
            log.error("NotFoundException with userId: {}", aLong);
            throw  new NotFoundException("User with id");
        });
    }

    @Override
    public Set<User> findAll() {
        log.info("class: UserSDJPAService, invoke method: findAll()");
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User save(User person) {
        log.info("class: UserSDJPAService, invoke method: save(User user), userId - {}", person.getId());
        return userRepository.save(person);
    }

    @Override
    public void delete(User user) {
        log.info("class: UserSDJPAService, invoke method: delete(User user), userId - {}", user.getId());
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long aLong) {
        log.info("class: UserSDJPAService, invoke method: deleteById(Long userId), userId - {}", aLong);
        userRepository.deleteById(aLong);
    }

    @Override
    public Set<User> findAllFollowersByUserId(Long userId) {
        log.info("class: UserSDJPAService, invoke method: findAllFollowersByUserId(Long userId), userId - {}", userId);
        return findById(userId)
                .getFollowers();
    }

    @Override
    public Set<User> findAllFollowingByUserId(Long userId) {
        log.info("class: UserSDJPAService, invoke method: findAllFollowersByUserId(Long userId), userId - {}", userId);
        return findById(userId)
                .getFollowing();
    }


}
