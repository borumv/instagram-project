package com.example.integration;

import com.example.InstagramApplication;
import com.example.entities.User;
import com.example.services.UserService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {InstagramApplication.class})
@Sql({
       "classpath:sql/data.sql"
})
public class UserServiceITTest extends IntegrationTestBase {
    private static final Long USER_ID = 1L;
    private final UserService userService;

    @Autowired
    public UserServiceITTest(UserService userService) {
        this.userService = userService;
    }
    @Test
    @Transactional
    void findById(){
        var user = userService.findById(USER_ID);
        System.out.println();
        assertEquals(USER_ID, user.getId());
        Assertions.assertThat(user.getPosts()).hasSize(1);
    }

    @Test
    void findByAll(){
        Iterable<User> usersIterable = userService.findAll();
        List<User> users = StreamSupport.stream(usersIterable.spliterator(), false)
                .collect(Collectors.toList());

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(3, users.size()); //

    }
}
