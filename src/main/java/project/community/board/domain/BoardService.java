package project.community.board.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.board.web.BoardDto;
import project.community.board.web.InsertBoard;
import project.community.board.web.UpdateBoard;
import project.community.comment.CommentDto;

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
    public List<CommentDto> findCmt(CommentDto commentDto){
        return boardDao.findCmt(commentDto);
    }
    public void viewUp(int uid){this.boardDao.viewUp(uid);}
}
