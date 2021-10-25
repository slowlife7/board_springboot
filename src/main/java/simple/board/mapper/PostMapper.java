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
    //void update(Long seq, Post updatePost);
    Long countAll();
    List<Post> findSkipAndLimit(final int skip, final int limit);
}