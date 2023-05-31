package com.example.services.sdjpa;

import com.example.entities.Profile;
import com.example.entities.User;
import com.example.exceptions.ProfileNotFoundException;
import com.example.exceptions.UserNotFoundException;
import com.example.repositories.ProfileRepository;
import com.example.services.sdjpa.ProfileSDJPAService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {
    static Long PROFILE_ID = 1L;
    Profile profile;
    Profile profile2;
    Profile profile3;
    @Mock
    ProfileRepository profileRepository;
    @InjectMocks
    ProfileSDJPAService profileService;

    @BeforeEach
    public void beforeAll() {
        profile = Profile.builder()
                .id(PROFILE_ID)
                .build();
        profile2 = Profile.builder()
                .id(2L)
                .build();
        profile3 = Profile.builder()
                .id(3L)
                .build();
    }

    @Test
    void findById(){
        //given
        when(profileRepository.findById(PROFILE_ID)).thenReturn(Optional.ofNullable(profile));
        //when
        Profile user = profileService.findById(PROFILE_ID);
        //then
        assertEquals(PROFILE_ID, user.getId());
        verify(profileRepository, times(1)).findById(PROFILE_ID);
    }

    @Test
    void findByIdThrowsMessageNotFoundException() {

        when(profileRepository.findById(PROFILE_ID)).thenReturn(Optional.empty());
        // Act & Assert
        Assertions.assertThrows(ProfileNotFoundException.class, () -> {
            profileService.findById(PROFILE_ID);
        });
        verify(profileRepository, times(1)).findById(PROFILE_ID);
    }

    @Test
    void findAllFollowersByUserId() {
        //given
        Set<Profile> followers = Set.of(profile, profile2);
        when(profileRepository.findAllFollowersByProfileId(PROFILE_ID)).thenReturn(followers);
        //when
        Set<Profile> userResponse = profileService.findAllFollowersByProfileId(PROFILE_ID);
        //then
        assertEquals(2, userResponse.size());
    }

    @Test
    void findAllFollowingByUserId() {
        //given
        Set<Profile> followers = Set.of(profile, profile2);
        when(profileRepository.findAllFollowingByProfileId(PROFILE_ID)).thenReturn(followers);
        //when
        Set<Profile> userResponse = profileService.findAllFollowingByProfileId(PROFILE_ID);
        //then
        assertEquals(2, userResponse.size());
    }

    @Test
    void findAll(){
        //given
        Set<Profile> allUsers = Set.of(profile, profile2, profile3);
        when(profileRepository.findAll()).thenReturn(allUsers);

        //when
        Set<Profile> profileResponse = profileService.findAll();

        //then
        assertEquals(3, profileResponse.size());
        verify(profileRepository, times(1)).findAll();
    }


    @Test
    void delete(){
        profileService.delete(profile);
        verify(profileRepository, times(1)).delete(profile);
    }

    @Test
    void deleteThrowsNotFoundException() {
        Profile userNull = Profile.builder().build();
        Assertions.assertThrows(ProfileNotFoundException.class, () -> {
            profileService.delete(userNull);
        });
        verifyNoInteractions(profileRepository);
    }

    @Test
    void deleteById(){
        profileService.deleteById(PROFILE_ID);
        verify(profileRepository, times(1)).deleteById(PROFILE_ID);
    }

}
