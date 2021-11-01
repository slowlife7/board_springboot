package simple.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import simple.board.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
@Repository
public interface PostMapper {
    void save(Post post);
    Post findById(Long id);
    List<Post> findAll();
    void updateOne(Post updatePost);
    void updateHitById(Long id);
    Long countAll();
    List<Post> findSkipAndLimit(final int skip, final int limit);
}
