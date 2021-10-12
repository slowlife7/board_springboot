package simple.board;

import org.junit.jupiter.api.Test;
import simple.board.model.Page;
import static org.assertj.core.api.Assertions.*;


class PaginationTest {

    private final Pagination pagination = new Pagination();

    @Test
    void getPageInfoPrevPageTest(){
        Page page = pagination.setTotalPostNum(61)
                            .setOnePagePostNum(10)
                            .setPageListNum(5)
                            .getPageInfo(6);

        assertThat(page.getStartPageNum()).isEqualTo(6);
        assertThat(page.getEndPageNum()).isEqualTo(7);
        assertThat(page.getPrevPageNum()).isEqualTo(5);
    }

    @Test
    void getPageInfoNextPageTest(){
        Page page = pagination.setTotalPostNum(61)
                .setOnePagePostNum(10)
                .setPageListNum(5)
                .getPageInfo(2);

        assertThat(page.getStartPageNum()).isEqualTo(1);
        assertThat(page.getEndPageNum()).isEqualTo(5);
        assertThat(page.getPrevPageNum()).isEqualTo(0);
        assertThat(page.getNextPageNum()).isEqualTo(6);
    }

    @Test
    void getPageInfoNotExistNextPage(){
        Page page = pagination.setTotalPostNum(61)
                .setOnePagePostNum(10)
                .setPageListNum(5)
                .getPageInfo(7);
        assertThat(page.getStartPageNum()).isEqualTo(6);
        assertThat(page.getEndPageNum()).isEqualTo(7);
        assertThat(page.getPrevPageNum()).isEqualTo(5);
        assertThat(page.getNextPageNum()).isEqualTo(0);
    }

    @Test
    void getPageInfoNonExistBothPrevNextPage(){
        Page page = pagination.setTotalPostNum(50)
                .setOnePagePostNum(10)
                .setPageListNum(5)
                .getPageInfo(5);
        assertThat(page.getStartPageNum()).isEqualTo(1);
        assertThat(page.getEndPageNum()).isEqualTo(5);
        assertThat(page.getPrevPageNum()).isEqualTo(0);
        assertThat(page.getNextPageNum()).isEqualTo(0);
    }
}