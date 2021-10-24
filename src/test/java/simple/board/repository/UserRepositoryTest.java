package simple.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import simple.board.model.User;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

   // UserRepository userRepository = new UserRepository();

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void register() {
        //given
        User user = new User();
        user.setId("billy");
        user.setPw("1234");
        user.setName("park");
        user.setEmail("rasgo@naver.com");
        //user.setDate(new Date());

        //when
        userRepository.register(user);
        Optional<User> findUser = userRepository.findById(user.getId());

        //then
        assertThat(findUser.isPresent()).isTrue();
        assertThat(findUser.get().getId()).isEqualTo(user.getId());
        assertThat(findUser.get().getPw()).isEqualTo(user.getPw());
    }

    @Test
    @Transactional
    void findById() {
        //given

        User user1 = new User();
        user1.setId("billy");
        user1.setPw("1234");
        user1.setName("park");
        user1.setEmail("rasgo@naver.com");
       // user1.setDate(new Date());

        User user2 = new User();
        user2.setId("billy");
        user2.setPw("1234");
        user2.setName("park");
        user2.setEmail("rasgo@naver.com");
       // user2.setDate(new Date());

        User user3 = new User();
        user3.setId("billy");
        user3.setPw("1234");
        user3.setName("park");
        user3.setEmail("rasgo@naver.com");
      //  user3.setDate(new Date());
        User user4 = new User();
        user4.setId("billy2");
        user4.setPw("1234");
        user4.setName("park");
        user4.setEmail("rasgo@naver.com");
    //    user4.setDate(new Date());
        User user5 = new User();
        user5.setId("billy");
        user5.setPw("1234");
        user5.setName("park");
        user5.setEmail("rasgo@naver.com");
    //    user5.setDate(new Date());

        //when
        userRepository.register(user1);
        userRepository.register(user2);
        userRepository.register(user3);
        userRepository.register(user4);
        userRepository.register(user5);

        Optional<User> findUser = userRepository.findById("billy2");
        //then
        assertThat(findUser.isPresent()).isTrue();
        assertThat(findUser.get().getId()).isEqualTo(user4.getId());
        assertThat(findUser.get().getPw()).isEqualTo(user4.getPw());
    }
}