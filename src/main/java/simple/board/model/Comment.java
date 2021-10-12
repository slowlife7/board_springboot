package simple.board.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private long    id;
    private long    postId;
    private long    userId;
    private String  content;
    private String  author;
    private Date date;
}
