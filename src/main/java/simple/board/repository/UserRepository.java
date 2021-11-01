package simple.board.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import simple.board.mapper.UserMapper;
import simple.board.model.Post;
import simple.board.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User register(User user) {
        userMapper.register(user);
        return user;
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMapper.findById(id));
    }
}
