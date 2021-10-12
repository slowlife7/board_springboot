package simple.board.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Page {
    private int startPageNum;
    private int endPageNum;
    private int prevPageNum;
    private int nextPageNum;
    private int currentPageNum;
}
