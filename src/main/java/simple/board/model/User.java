package simple.board.model;

import lombok.*;

import java.util.Date;

@Data @ToString
public class User {
    private Long seq;
    private String id;
    private String pw;
    private String name;
    private String email;
    private Date create_date;
}