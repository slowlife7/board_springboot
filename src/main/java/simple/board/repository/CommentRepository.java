package simple.board.repository;

import org.springframework.stereotype.Repository;
import simple.board.model.Comment;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private static final Map<Long, Comment> store = new HashMap<>();
    private static long sequence = 0L;

    public Comment save(Comment comment) {
        comment.setId(++sequence);
        comment.setDate(new Date());
        store.put(comment.getId(), comment);
        return comment;
    }

    public List<Comment> findByPostId(final long postId) {

        List<Comment> commentList = store.values()
                .stream()
                .filter( s -> s.getPostId() == postId)
                .collect(Collectors.toList());
        Collections.reverse(commentList);

        return commentList;
    }

    public Comment findById(final long id) {
        return store.get(id);
    }

    public void clearStore() {
        store.clear();
    }

    public Comment updateComment(final long postId, Comment comment) {
        List<Comment> comments = findByPostId(postId);

        Optional<Comment> findComment = comments.stream()
                .filter(c -> c.getId() == comment.getId())
                .findAny();

        if (findComment.isPresent()) {
            Comment originComment = findComment.get();
            originComment.setContent(comment.getContent());
            return originComment;
        }

        return null;
    }
}
