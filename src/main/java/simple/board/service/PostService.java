package simple.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.board.Pagination;
import simple.board.model.*;
import simple.board.repository.CommentRepository;
import simple.board.repository.PostRepository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private static final int PAGE_LIST_NUM = 5;
    private static final int ONE_PAGE_POST_NUM = 10;
    //private PostRepository postRepository;

    PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

   /* @PostConstruct
    void init() {
        for(int i = 0; i<101; i++){
            Post post = new Post();
            post.setTitle("Hello"+i);
            post.setAuthor("billy"+i);
            post.setHit(0);
            post.setDate(new Date());
            post.setContent("hello!!!!!!!"+i);
            postRepository.save(post);

            for(int j = 0; j < 10; j++) {
                Comment comment = new Comment();
                comment.setPostId(post.getSeq());
                comment.setContent("test" + j);
                comment.setAuthor("herry" + j);
                comment.setDate(new Date());
                commentRepository.save(comment);
            }
        }
    }*/

    public PostListWithPageInfo findPage(final int currentPageNum) {

        int currentPageNumIndex = currentPageNum - 1;
        int skip = currentPageNumIndex * ONE_PAGE_POST_NUM;
        int limit = ONE_PAGE_POST_NUM;

        List<Post> postList = postRepository.findSkipAndLimit(skip, limit);

        Pagination pagination = new Pagination();
        int postCount = postRepository.countAll().intValue();

        Page pageInfo = pagination.setTotalPostNum(postCount)
                .setPageListNum(PAGE_LIST_NUM)
                .setOnePagePostNum(ONE_PAGE_POST_NUM)
                .getPageInfo(currentPageNum);

        return new PostListWithPageInfo(postList, pageInfo);
    }

    public PostComment getPostWithComments(final long id) {
        Post findPost = postRepository.findById(id);
        
        long hit = findPost.getHit();
        findPost.setHit(hit+1);

        List<Comment> findComments = commentRepository.findByPostId(id);

        return new PostComment(findPost, findComments);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Comment saveCommentToPost(final long postId, Comment comment) {

        Post findPost = postRepository.findById(postId);
        if(findPost == null) {
            return null;
        }

        comment.setPostId(findPost.getSeq());
        return commentRepository.save(comment);
    }

    public Post findPostById(final Long postId) {
        return postRepository.findById(postId);
    }

    public void updateById(final Long postId, Post post) {
        postRepository.update(postId, post);
    }
}
