package com.example.repositories;

import com.example.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    /*
    / Get all users that the user is following
     */
    @Query(value = "SELECT profile_id, user_id, nickname, photo\n" +
            "FROM profiles\n" +
            "WHERE user_id = ANY (SELECT followers.user_id as user_id\n" +
            "                    FROM profiles\n" +
            "                    JOIN followers ON profiles.user_id = followers.friend_id\n" +
            "                    WHERE followers.friend_id = :userId)", nativeQuery = true)
    Set<Profile> findAllFollowingByProfileId(Long userId);

    /*
    / Get all users who follow a user
     */
    @Query(value = "SELECT profile_id, user_id, nickname, photo\n" +
            "FROM profiles\n" +
            "WHERE user_id = ANY (SELECT  followers.friend_id as user_id\n" +
            "                FROM profiles\n" +
            "                JOIN followers ON profiles.user_id = followers.user_id\n" +
            "                WHERE followers.user_id = :userId)", nativeQuery = true)
    Set<Profile> findAllFollowersByProfileId(Long userId);
}
