package com.example.services;

import com.example.entities.Profile;
import java.util.Set;

public interface ProfileService extends CrudService<Profile, Long> {
    Set<Profile> findAllFollowingByProfileId(Long userId);
    Set<Profile> findAllFollowersByProfileId(Long userId);
}
