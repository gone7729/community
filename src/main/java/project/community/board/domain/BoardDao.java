package project.community.board.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.board.web.BoardDto;
import project.community.board.web.BoardRecDto;
import project.community.board.web.SearchDto;
import project.community.comment.web.CommentDto;
import project.community.comment.web.ReplyDto;
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
    public List<CommentDto> findCmt(int board_uid){
        return mapper.findCmt(board_uid);
    }
    public List<ReplyDto> findReply(ReplyDto replyDto){
        return mapper.findReply(replyDto.getCmt_uid());
    }
    public void viewUp(int uid){this.mapper.viewUp(uid);}
    public void boardPointUp(BoardRecDto boardRecDto){this.mapper.boardPointUp(boardRecDto);}
    public void boardPointDown(BoardRecDto boardRecDto){this.mapper.boardPointDown(boardRecDto);}
    public int findRecEmail(String email, int board_uid){return mapper.findRecEmail(email, board_uid);}
    public List<SearchDto> searchBoardList(SearchDto searchDto){
        return mapper.searchBoardList(searchDto);
    }
    public int searchCount(String ctg, String text){
        return mapper.searchCount(ctg, text);
    }
}
