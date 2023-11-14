package project.community.util;

import lombok.Data;

@Data
public class Paging {
    private int startPage;
    private int endPage;
    private int nowPage;
    private int lastPage;
    private int pageCount;
    private int pageSize;
    private int total; //불러온 게시글 개수

    public Paging(int total, int nowPage, int pageSize, int pageCount) {
        this.total = total;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        calcLastPage(getTotal(), getPageSize());
        calcStartEndPage(getNowPage(), pageCount);
        setStartPage(getStartPage());
        setEndPage(getEndPage());
    }
    //총 게시 글 수로 계산한 마지막 페이지 계산
    public void calcLastPage(int total, int pageSize){
        setLastPage((total/pageSize));
        if(total % pageSize != 0){
            setLastPage((total/pageSize)+1);
        }
    }
    //첫 페이지와 마지막 페이지 계산
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



