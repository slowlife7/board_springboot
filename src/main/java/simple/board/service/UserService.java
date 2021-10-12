package simple.board.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.board.model.User;
import simple.board.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    void init() {
        User user = new User();
        user.setId("herry1");
        user.setPw("1234");
        user.setName("herry1");
        user.setEmail("rasgo@naver.com");
        userRepository.register(user);

        User user1 = new User();
        user1.setId("billy98");
        user1.setPw("1234");
        user1.setName("billy98");
        user1.setEmail("rasgo1@naver.com");
        userRepository.register(user1);
    }

    public boolean isDuplicateId(String id){
        Optional<User> findId = userRepository.findById(id);
        return findId.isPresent();
    }

    public User register(User user) {
        return userRepository.register(user);
    }

    public User login(User user) {
        return userRepository.findById(user.getId())
                .filter(u -> u.getPw().equals(user.getPw())).orElse(null);
    }
}
