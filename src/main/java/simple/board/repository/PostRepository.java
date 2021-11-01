package simple.board.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import simple.board.mapper.PostMapper;
import simple.board.model.Post;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    /*private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;*/
    @Autowired
    private PostMapper postMapper;

    public Post save(Post post) {
        postMapper.save(post);
        return post;
        /*post.setSeq(++sequence);
        post.setDate(new Date());
        store.put(post.getSeq(), post);
        return post;*/
    }

    @Transactional
    public Post findById(Long id) {
        Post post = postMapper.findById(id);
        postMapper.updateHitById(id);
        return post;
        /*return store.get(id);*/
    }

    public List<Post> findAll() {
        return postMapper.findAll();
        /*return new ArrayList<>(store.values());*/
    }

    public void updateOne(Post updatePost) {
        postMapper.updateOne(updatePost);
        //postMapper.update(id,updatePost);
    }

    public Long countAll(){
        return postMapper.countAll();
        /*return store.values().stream().count();*/
    }

    public List<Post> findSkipAndLimit(final int skip, final int limit){
        return postMapper.findSkipAndLimit(skip, limit);
        /*List<Post> postList = store.values().stream()
                .collect(Collectors.toList());
        Collections.reverse(postList);

        return postList.stream()
                .skip(skip).limit(limit)
                .collect(Collectors.toList());*/
    }

    /*public void clearStore() {
        store.clear();
    }*/
}
