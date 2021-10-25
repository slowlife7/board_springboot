package simple.board.model;

import lombok.*;

import java.util.Date;

@Data
public class Post {
    private long    seq;
    private String  title;
    private String  author;
    private String  content;
    private long    hit;
    private Date    createDate;
}

