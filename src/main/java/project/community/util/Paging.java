package project.community.util;

import lombok.Data;

@Data
public class Paging {
    private int startPage;
    // nP/pC*pC +1, nP/pC = 1 이라면 sP -= pC
    private int endPage;
    //sP+pC-1, tP가 eP보다 작다면 eP = tP
    private int nowPage;
    private int lastPage;
    private int pageCount =10; //10
    private int pageSize;
    private int total; //불러온 게시글 개수

    public Paging(int total, int nowPage, int pageSize) {
        this.total = total;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
        calcLastPage(getTotal(), getPageSize());
        calcStartEndPage(getNowPage(), pageCount);
        setStartPage(getStartPage());
        setEndPage(getEndPage());
    }

    public void calcLastPage(int total, int pageSize){
        setLastPage((total/pageSize));
        if(total % pageSize != 0){
            setLastPage((total/pageSize)+1);
        }
    }

    public void calcStartEndPage(int nowPage, int pageCount){
        setStartPage((nowPage/pageCount)*pageCount+1);
        if(nowPage % pageCount == 0){
            setStartPage(startPage -= pageCount);
        }

        setEndPage(startPage + pageCount -1);
        if (endPage > lastPage){
            setEndPage(getLastPage());
        }
    }
}



