package project.community.board.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.board.web.*;
import project.community.comment.web.CommentDto;
import project.community.comment.web.ReplyDto;

import java.util.List;

@Service
public class BoardService {

    BoardDao boardDao;

    @Autowired
    public BoardService(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public List<BoardDto> findAllBoard(){
        return boardDao.findAllBoard();
    }

    public void insertBoard(InsertBoard insertBoard){
        Board board = new Board();

        board.setCategoryNo(insertBoard.getCategoryNo());
        board.setTitle(insertBoard.getTitle());
        board.setContent(insertBoard.getContent());
        board.setWriter(insertBoard.getWriter());

        boardDao.insertBoard(board);
    }

    public int countBoard(){
        return boardDao.countBoard();
    }

    public List<BoardDto> findBoardList(BoardDto boardDto){
        return boardDao.findBoardList(boardDto);
    }
    public List<BoardDto> findHomeBoardList(BoardDto boardDto){
        return boardDao.findHomeBoardList(boardDto);
    }

    public BoardDto findBoard(BoardDto boardDto){
        Board board = new Board();

        board.setUid(boardDto.getUid());
        return boardDao.findBoard(board);
    }
    public void updateBoard(UpdateBoard updateBoard){
        Board board = new Board();

        board.setCategoryNo(updateBoard.getCategoryNo());
        board.setWriter(updateBoard.getWriter());
        board.setTitle(updateBoard.getTitle());
        board.setContent(updateBoard.getContent());
        this.boardDao.updateBoard(board);
    }
    public void deleteBoard(BoardDto boardDto){
        this.boardDao.deleteBoard(boardDto);
    }
    public List<CommentDto> findCmt(int board_uid){
        return boardDao.findCmt(board_uid);
    }
    public List<ReplyDto> findReply(ReplyDto replyDto){
        return boardDao.findReply(replyDto);
    }
    public void viewUp(int uid){this.boardDao.viewUp(uid);}
    public void boardPointUp(BoardRecDto boardRecDto){this.boardDao.boardPointUp(boardRecDto);}
    public void boardPointDown(BoardRecDto boardRecDto){this.boardDao.boardPointDown(boardRecDto);}
    public int findRecEmail(String email, int board_uid){return boardDao.findRecEmail(email, board_uid);}
    public List<SearchDto> searchBoardList(SearchDto searchDto){
        return boardDao.searchBoardList(searchDto);
    }
    public int searchCount(String ctg, String text){
        return boardDao.searchCount(ctg, text);
    }
}
