package com.example.controllers;

import com.example.entities.Profile;
import com.example.models.dto.UserDTO;
import com.example.models.mappers.UserMapper;
import com.example.services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;
    private final UserMapper userMapper;
    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable Long id) {
        return profileService.findById(id);
    }
    @GetMapping("/{id}/followers")
    public Set<Profile> getAllFollowers(@PathVariable Long id){
        return new HashSet<>(profileService.findAllFollowersByProfileId(id));
    }
    @GetMapping("/{id}/followings")
    public Set<Profile> getAllFollowings(@PathVariable Long id){
        return profileService.findAllFollowingByProfileId(id);
    }
}
