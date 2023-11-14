package project.community.board.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.board.web.BoardDto;
import project.community.board.web.InsertBoard;
import project.community.board.web.RecDto;
import project.community.board.web.SearchDto;
import project.community.comment.CommentDto;
import project.community.comment.ReplyDto;
import project.community.mybatis.mappers.Mapper;

import java.util.List;

@Repository
public class BoardDao {
    Mapper mapper;

    @Autowired
    public BoardDao(Mapper mapper){
        this.mapper = mapper;
    }

    public List<BoardDto> findAllBoard(){
        return mapper.findAllBoard();
    }

    public void insertBoard(Board Board){
        this.mapper.insertBoard(Board);
    }

    public int countBoard(){
        return mapper.countBoard();
    }

    public List<BoardDto> findBoardList(BoardDto boardDto) {
        return mapper.findBoardList(boardDto);
    }
    public List<BoardDto> findHomeBoardList(BoardDto boardDto) {
        return mapper.findHomeBoardList(boardDto);
    }
    public BoardDto findBoard(Board board){
        return mapper.findBoard(board);
    }
    public void updateBoard(Board board){
        this.mapper.updateBoard(board);
    }
    public void deleteBoard(BoardDto boardDto){
        this.mapper.deleteBoard(boardDto);
    }
    public List<CommentDto> findCmt(CommentDto commentDto){
        return mapper.findCmt(commentDto);
    }
    public List<ReplyDto> findReply(ReplyDto replyDto){
        return mapper.findReply(replyDto);
    }
    public void viewUp(int uid){this.mapper.viewUp(uid);}
    public void pointUp(RecDto recDto){this.mapper.pointUp(recDto);}
    public int findRecEmail(String email){return mapper.findRecEmail(email);}
    public List<SearchDto> searchBoardList(SearchDto searchDto){
        return mapper.searchBoardList(searchDto);
    }
    public int searchCount(String ctg, String text){
        return mapper.searchCount(ctg, text);
    }
}
