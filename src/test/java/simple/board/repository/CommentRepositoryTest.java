package simple.board.repository;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simple.board.model.Comment;
import simple.board.model.Post;
import simple.board.model.User;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryTest {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @BeforeEach
    void init(){
        commentRepository = new CommentRepository();
        postRepository = new PostRepository();
    }

    @AfterEach
    void clear(){
        commentRepository.clearStore();
    }

    @Test
    void findByPostId() {
        //given
        Post post = new Post();
        post.setTitle("hello");
        post.setAuthor("billy");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Comment comment1 = new Comment();
        comment1.setPostId(post.getSeq());
        comment1.setContent("empty1");
        comment1.setAuthor("tester1");
        comment1.setDate(new Date());

        Comment comment2 = new Comment();
        comment2.setPostId(post.getSeq());
        comment2.setContent("empty2");
        comment2.setAuthor("tester2");
        comment2.setDate(new Date());

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        //when
        List<Comment> findComments = commentRepository.findByPostId(post.getSeq());

        //then
        assertThat(findComments.size()).isEqualTo(2);
        assertThat(findComments).contains(comment2, Index.atIndex(0));
    }

    @Test
    void updateComment() {
        //given
        Post post = new Post();
        post.setTitle("hello");
        post.setAuthor("billy");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setPostId(post.getSeq());
        comment.setContent("empty1");
        comment.setAuthor("tester1");
        comment.setDate(new Date());

        commentRepository.save(comment);

        Comment comment1 = new Comment();
        comment1.setPostId(post.getSeq());
        comment1.setContent("empty2");
        comment1.setAuthor("tester1");
        comment1.setDate(new Date());
        comment1.setId(comment.getId());

        Comment updateComment = commentRepository.updateComment(post.getSeq(), comment1);

        assertThat(updateComment.getContent()).isEqualTo(comment1.getContent());
        assertThat(updateComment.getAuthor()).isEqualTo(comment1.getAuthor());
    }
}