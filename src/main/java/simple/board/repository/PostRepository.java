package simple.board.repository;

import org.springframework.stereotype.Repository;
import simple.board.model.Post;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    public Post save(Post post) {
        post.setNumber(++sequence);
        post.setDate(new Date());
        store.put(post.getNumber(), post);
        return post;
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Post updatePost) {
        Post post = findById(id);
        post.setTitle(updatePost.getTitle());
        post.setAuthor(updatePost.getAuthor());
        post.setContent(updatePost.getContent());
        //post.setHit(updatePost.getHit());
        //post.setDate(updatePost.getDate());
    }

    public Long countAll(){
        return store.values().stream().count();
    }

    public List<Post> findSkipAndLimit(final int skip, final int limit){

        List<Post> postList = store.values().stream()
                .collect(Collectors.toList());
        Collections.reverse(postList);

        return postList.stream()
                .skip(skip).limit(limit)
                .collect(Collectors.toList());
    }

    public void clearStore() {
        store.clear();
    }
}
