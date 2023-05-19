import com.example.InstagramApplication;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {InstagramApplication.class})
@ExtendWith(SpringExtension.class)
public class DataITTest {
    private static final Long USER_ID = 1L;
    private final UserRepository userRepository;

    @Autowired
    public DataITTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @Transactional
    void findById(){
        var user = userRepository.findById(USER_ID);
        System.out.println();
        assertTrue(user.isPresent());
        Assertions.assertThat(user.get().getPosts()).hasSize(1);
    }

    @Test
    void findByAll(){
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> users = StreamSupport.stream(usersIterable.spliterator(), false)
                .collect(Collectors.toList());

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(3, users.size()); //

    }
}
