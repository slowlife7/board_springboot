package simple.board.repository;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import simple.board.model.Comment;
import simple.board.model.Post;
import simple.board.model.User;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void save() {
        Post post = new Post();
        post.setTitle("hello");
        post.setContent("test1");
        post.setAuthor("billy");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Comment comment1 = new Comment();
        comment1.setPostId(post.getSeq());
        comment1.setContent("empty1");
        comment1.setAuthor("tester1");

        Comment saveComment = commentRepository.save(comment1);

        Assertions.assertThat(saveComment).isEqualTo(comment1);
    }

    @Test
    @Transactional
    void findByPostId() {
        //given
        Post post = new Post();
        post.setTitle("hello");
        post.setAuthor("billy");
        post.setContent("test111");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Comment comment1 = new Comment();
        comment1.setPostId(post.getSeq());
        comment1.setContent("empty1");
        comment1.setAuthor("tester1");

        Comment comment2 = new Comment();
        comment2.setPostId(post.getSeq());
        comment2.setContent("empty2");
        comment2.setAuthor("tester2");

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        //when
        List<Comment> findComments = commentRepository.findByPostId(post.getSeq());

        //then
        assertThat(findComments.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void updateComment() {
        //given
        Post post = new Post();
        post.setTitle("hello");
        post.setAuthor("billy");
        post.setContent("test11");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setPostId(post.getSeq());
        comment.setContent("empty1");
        comment.setAuthor("tester1");

        commentRepository.save(comment);

        comment.setContent("tester2");

        Comment updateComment = commentRepository.updateComment(comment);

        assertThat(updateComment.getContent()).isEqualTo(comment.getContent());
    }
}