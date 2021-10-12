package simple.board.repository;

import org.springframework.stereotype.Repository;
import simple.board.model.Post;
import simple.board.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User register(User user) {
        user.setNumber(++sequence);
        store.put(user.getNumber(), user);
        return user;
    }

    public Optional<User> findById(String id) {
        return store.values().stream()
                .filter(u -> u.getId().equals(id)).findAny();
    }

    public void clearStore() {
        store.clear();
    }
}
