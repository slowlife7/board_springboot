package simple.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import simple.board.model.User;
import simple.board.repository.PostRepository;
import simple.board.repository.UserRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserRepository userRepository = new UserRepository();
    private UserService userService = new UserService(userRepository);

    @AfterEach
    void tearDown(){
        userRepository.clearStore();
    }

    @Test
    void isDuplicateId() {

        /*User user = new User("billy", "1234", "park","test@test.com", new Date());*/
        User user = new User();
        user.setId("billy");
        user.setPw("1234");
        user.setName("park");
        user.setEmail("rasgo@naver.com");
        user.setDate(new Date());
        userRepository.register(user);

        boolean duplicated = userService.isDuplicateId(user.getId());
        Assertions.assertThat(duplicated).isTrue();
    }

    @Test
    void register() {
        User user = new User();
        user.setId("billy");
        user.setPw("1234");
        user.setName("park");
        user.setEmail("rasgo@naver.com");
        user.setDate(new Date());

        User register = userService.register(user);

        Assertions.assertThat(register).isEqualTo(user);
    }

    @Test
    void loginTest() {

        User user = new User();
        user.setId("billy1");
        user.setPw("1234");

        User register = userService.register(user);
        Assertions.assertThat(register).isEqualTo(user);

        User loginUser = new User();
        loginUser.setId("billy1");
        loginUser.setPw("1234");

        User result = userService.login(loginUser);
        Assertions.assertThat(result).isEqualTo(user);
    }
}