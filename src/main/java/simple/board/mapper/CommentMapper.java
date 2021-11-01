package simple.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import simple.board.model.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    void save(Comment comment);
    List<Comment> findByPostId(final long postId);
    void updateComment(Comment comment);
}
