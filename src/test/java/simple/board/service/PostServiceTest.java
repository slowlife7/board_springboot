package simple.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simple.board.model.Comment;
import simple.board.model.PostComment;
import simple.board.model.PostListWithPageInfo;
import simple.board.model.Post;
import simple.board.repository.CommentRepository;
import simple.board.repository.PostRepository;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostServiceTest {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepository();
        commentRepository = new CommentRepository();
        postService = new PostService(postRepository, commentRepository);
    }

    @AfterEach
    void tearDown(){
        //postRepository.clearStore();
        commentRepository.clearStore();
    }

    @Test
    void findPage() {

        for(int i = 0; i<101; i++){
            Post post = new Post();
            post.setTitle("hello"+i);
            post.setAuthor("billy");
            post.setHit(0);
            //post.setDate(new Date());
            postRepository.save(post);
        }

        PostListWithPageInfo postWithPage = postService.findPage(1);

        assertThat(postWithPage.getPosts().size()).isEqualTo(10);
        assertThat(postWithPage.getPage().getStartPageNum()).isEqualTo(1);
        assertThat(postWithPage.getPage().getEndPageNum()).isEqualTo(5);
        assertThat(postWithPage.getPage().getPrevPageNum()).isEqualTo(0);
        assertThat(postWithPage.getPage().getNextPageNum()).isEqualTo(6);

        postWithPage = postService.findPage(6);
        assertThat(postWithPage.getPosts().size()).isEqualTo(10);
        assertThat(postWithPage.getPage().getStartPageNum()).isEqualTo(6);
        assertThat(postWithPage.getPage().getEndPageNum()).isEqualTo(10);
        assertThat(postWithPage.getPage().getPrevPageNum()).isEqualTo(5);
        assertThat(postWithPage.getPage().getNextPageNum()).isEqualTo(11);
    }

    @Test
    void getPostWithCommentsTest(){

        //given
        Post post = new Post();
        post.setTitle("hello");
        post.setAuthor("billy");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        for(int j = 0; j<10; j++) {
            Comment comment = new Comment();
            comment.setPostId(post.getSeq());
            comment.setContent("test" + j);
            comment.setAuthor("test" + j);
            comment.setDate(new Date());
            commentRepository.save(comment);
        }

        //when
        PostComment postWithComments = postService.getPostWithComments(post.getSeq());

        //then
        assertThat(postWithComments).isNotNull();
        assertThat(postWithComments.getPost()).isEqualTo(post);
        assertThat(postWithComments.getComments().size()).isEqualTo(10);
    }

    @Test
    void savePost() {
        //given
        Post post = new Post();
        post.setTitle("test!!!");
        post.setAuthor("carry");
        post.setHit(0);
        //post.setDate(new Date());

        //when
        Post result = postService.savePost(post);

        //then
        assertThat(post).isEqualTo(result);
    }

    @Test
    void saveCommentToPost() {
        //given
        Post post = new Post();
        post.setTitle("post!!");
        post.setAuthor("billy");
        post.setContent("hello!!");
        //post.setDate(new Date());
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("sibal");
        comment.setAuthor("herry");

        //when
        postService.saveCommentToPost(post.getSeq(), comment);

        //then
        PostComment postWithComments = postService.getPostWithComments(post.getSeq());
        assertThat(postWithComments.getComments()).contains(comment);
    }

    @Test
    void findPostByIdTest() {

        Post post = new Post();
        post.setTitle("findTest1");
        post.setAuthor("billy");
        post.setContent("empty");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Post findPost = postService.findPostById(post.getSeq());
        assertThat(findPost).isNotNull();
        assertThat(findPost.getSeq()).isEqualTo(post.getSeq());
    }

    @Test
    void updatePostTest(){
        Post post = new Post();
        post.setTitle("findTest1");
        post.setAuthor("billy");
        post.setContent("empty");
        post.setHit(0);
        //post.setDate(new Date());
        postRepository.save(post);

        Post post1 = new Post();
        post1.setTitle("findTest12");
        post1.setAuthor("billy12");
        post1.setContent("empty12");
        post1.setHit(0);
        //post1.setDate(new Date());
        postRepository.save(post1);

        postService.updateById(post.getSeq(), post1);

        Post findPost = postService.findPostById(post.getSeq());

        assertThat(findPost.getTitle()).isEqualTo(post1.getTitle());
        assertThat(findPost.getAuthor()).isEqualTo(post1.getAuthor());
    }
}