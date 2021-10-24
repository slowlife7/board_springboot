package simple.board.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import simple.board.model.User;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    void register() {
        User userDao = new User();
        userDao.setId("billy");
        userDao.setPw("1234");
        userDao.setName("hahaha");
        userDao.setEmail("test@test.com");

        userMapper.register(userDao);
    }
}