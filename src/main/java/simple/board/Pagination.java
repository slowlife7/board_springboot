package simple.board;

import simple.board.model.Page;

public class Pagination {

    private int totalPostNum = 0;
    private int onePagePostNum = 10;
    private int pageListNum = 5;

    public Pagination setOnePagePostNum(final int onePagePostNum){
        this.onePagePostNum = onePagePostNum;
        return this;
    }

    public Pagination setPageListNum(final int pageListNum) {
        this.pageListNum = pageListNum;
        return this;
    }

    public Pagination setTotalPostNum(final int totalPostNum) {
        this.totalPostNum = totalPostNum;
        return this;
    }

    public Page getPageInfo(final int currentPageNum) {
        int totalPageNum = (int)Math.ceil( (totalPostNum / (double)onePagePostNum));

        int pageGroupNum =(int) Math.ceil( (currentPageNum / (double)pageListNum));
        int startPageNum = (pageGroupNum-1)* pageListNum+1;
        int endPageNum = pageGroupNum * pageListNum;
        if(endPageNum > totalPageNum) {
            endPageNum = totalPageNum;
        }

        int prevPageNum = startPageNum - 1;
        int nextPageNum = (endPageNum < totalPageNum)? endPageNum + 1: 0;

        Page page = new Page();
        page.setStartPageNum(startPageNum);
        page.setEndPageNum(endPageNum);
        page.setPrevPageNum(prevPageNum);
        page.setNextPageNum(nextPageNum);
        page.setCurrentPageNum(currentPageNum);

        return page;
    }
}
