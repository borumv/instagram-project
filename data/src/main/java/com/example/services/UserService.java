package com.example.services;

import com.example.entities.Post;
import com.example.entities.Profile;
import com.example.entities.User;

import java.util.Set;

public interface UserService extends CrudService<User, Long> {
    Set<User> findAllFollowersByUserId(Long userId);
    Set<User> findAllFollowingByUserId(Long userId);

}
