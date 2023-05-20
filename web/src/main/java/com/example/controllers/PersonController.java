package com.example.controllers;

import com.example.entities.User;
import com.example.entities.dto.UserDTO;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final UserService userService;
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return UserDTO.toUserDTO(userService.findById(id));
    }

}
