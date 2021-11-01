package simple.board.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import simple.board.mapper.CommentMapper;
import simple.board.model.Comment;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    CommentMapper commentMapper;

    @Autowired
    CommentRepository(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Comment save(Comment comment) {
        commentMapper.save(comment);
        return comment;
    }

    public List<Comment> findByPostId(final long postId) {
        return commentMapper.findByPostId(postId);
    }

    public Comment updateComment(Comment comment) {
        commentMapper.updateComment(comment);
        return comment;
    }
}
