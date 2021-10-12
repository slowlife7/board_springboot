package simple.board.repository;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import simple.board.model.Post;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostRepositoryTest {

    PostRepository postRepository = new PostRepository();

    @AfterEach
    void clear(){
        postRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Post post = new Post();
        post.setTitle("hello");
        post.setAuthor("billy");
        post.setHit(0);
        post.setDate(new Date());

        //when
        postRepository.save(post);
        //then
        Post findPost = postRepository.findById(post.getNumber());
        assertThat(findPost).isEqualTo(post);
    }

    @Test
    void findAll() {
        //given
        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();

        post1.setTitle("hello1");
        post1.setAuthor("billy");
        post1.setHit(0);
        post1.setDate(new Date());

        post2.setTitle("hello2");
        post2.setAuthor("billy");
        post2.setHit(0);
        post2.setDate(new Date());

        post3.setTitle("hello3");
        post3.setAuthor("billy");
        post3.setHit(0);
        post3.setDate(new Date());

        
        //when
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        //then
        List<Post> posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(3);
        assertThat(posts).contains(post1, post2, post3);
    }

    @Test
    void update(){
        //given
        Post oldOne = new Post();
        Post newOne = new Post();

        oldOne.setTitle("hello");
        oldOne.setAuthor("billy");
        oldOne.setHit(0);
        oldOne.setDate(new Date());

        newOne.setTitle("hello1");
        newOne.setAuthor("billy1");
        newOne.setHit(1);
        newOne.setDate(new Date());

        //when
        postRepository.save(oldOne);
        postRepository.update(oldOne.getNumber(), newOne);
        //then
        assertThat(oldOne.getTitle()).isEqualTo(newOne.getTitle());
        assertThat(oldOne.getAuthor()).isEqualTo(newOne.getAuthor());
        assertThat(oldOne.getHit()).isEqualTo(newOne.getHit());
        assertThat(oldOne.getDate()).isEqualTo(newOne.getDate());
    }

    @Test
    void findSkipAndLimit(){

        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        Post post4 = new Post();
        Post post5 = new Post();

        post1.setTitle("title1");
        post1.setAuthor("billy");
        post1.setHit(0);
        post1.setDate(new Date());

        post2.setTitle("title2");
        post2.setAuthor("billy");
        post2.setHit(0);
        post2.setDate(new Date());

        post3.setTitle("title3");
        post3.setAuthor("billy");
        post3.setHit(0);
        post3.setDate(new Date());

        post4.setTitle("title4");
        post4.setAuthor("billy");
        post4.setHit(0);
        post4.setDate(new Date());

        post5.setTitle("title5");
        post5.setAuthor("billy");
        post5.setHit(0);
        post5.setDate(new Date());

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        postRepository.save(post5);

        List<Post> posts = postRepository.findSkipAndLimit(1, 5);
        assertThat(posts.size()).isEqualTo(4);
        assertThat(posts.get(0)).isEqualTo(post4);
        assertThat(posts.get(3)).isEqualTo(post1);
        assertThat(posts).contains(post4, Index.atIndex(0));
        assertThat(posts).contains(post1, Index.atIndex(3));
    }
}