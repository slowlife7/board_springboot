package simple.board.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @RequiredArgsConstructor
public class PostListWithPageInfo {
    private final List<Post> posts;
    private final Page       page;
}
