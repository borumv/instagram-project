package com.example.services.sdjpa;

import com.example.entities.Profile;
import com.example.entities.User;
import com.example.exceptions.NotFoundException;
import com.example.repositories.ProfileRepository;
import com.example.services.ProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class ProfileSDJPAService implements ProfileService {

    private final ProfileRepository profileRepository;
    @Override
    public Profile findById(Long aLong) {
            return profileRepository.findById(aLong).orElseThrow(() -> {
                throw  new NotFoundException(String.format("Profile with id %d", aLong));
            });
        }


    @Override
    public Set<Profile> findAll() {
        Set<Profile> users = new HashSet<>();
        profileRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Profile save(Profile person) {
        return profileRepository.save(person);
    }

    @Override
    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }

    @Override
    public void deleteById(Long aLong) {
        profileRepository.deleteById(aLong);
    }
}
