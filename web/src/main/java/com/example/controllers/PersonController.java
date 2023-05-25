package com.example.controllers;

import com.example.models.dto.UserDTO;
import com.example.models.mappers.UserMapper;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userMapper.userToUserDTO(userService.findById(id));
    }
    @GetMapping("/{id}/followers")
    public Set<UserDTO> getAllFollowers(@PathVariable Long id){
        //User user = userService.findById(id);
        return userService.findAllFollowersByUserId(id)
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toSet());
    }
    @GetMapping("/{id}/followings")
    public Set<UserDTO> getAllFollowings(@PathVariable Long id){
        return userService.findAllFollowingByUserId(id)
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toSet());
    }
}
