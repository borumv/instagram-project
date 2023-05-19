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
       log.info("class: ProfileSDJPAService, invoke method: findById(),  id: {}", aLong);
            return profileRepository.findById(aLong).orElseThrow(() -> {
                log.error("NotFoundException with profileId: {}", aLong);
                throw  new NotFoundException(String.format("Profile with id %d", aLong));
            });
        }


    @Override
    public Set<Profile> findAll() {
        log.info("class: ProfileSDJPAService, invoke method: findAll()");
        Set<Profile> users = new HashSet<>();
        profileRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Profile save(Profile person) {
        log.info("class: UserSDJPAService, invoke method: save(User user), userId - {}", person.getId());
        return profileRepository.save(person);
    }

    @Override
    public void delete(Profile profile) {
        log.info("class: UserSDJPAService, invoke method: delete(User user), userId - {}", profile.getId());
        profileRepository.delete(profile);
    }

    @Override
    public void deleteById(Long aLong) {
        log.info("class: UserSDJPAService, invoke method: deleteById(Long userId), userId - {}", aLong);
        profileRepository.deleteById(aLong);
    }
}
