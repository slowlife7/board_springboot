package simple.board.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @RequiredArgsConstructor
public class PostComment {
    private final Post post;
    private final List<Comment> comments;
}
