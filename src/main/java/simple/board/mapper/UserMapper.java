package simple.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import simple.board.model.User;

@Mapper
public interface UserMapper {

    void register(User user);
    User findById(String id);
}
