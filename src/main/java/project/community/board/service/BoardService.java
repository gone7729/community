package project.community.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.board.dto.BoardDto;
import project.community.board.dao.BoardDao;
import project.community.board.dto.ListSetDto;
import project.community.comment.dto.CommentDto;

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

    public void insertBoard(BoardDto boardDto){
        boardDao.insertBoard(boardDto);
    }

    public int countBoard(){
        return boardDao.countBoard();
    }

    public List<BoardDto> findBoardList(BoardDto boardDto){
        return boardDao.findBoardList(boardDto);
    }

    public BoardDto findBoard(BoardDto boardDto){
        return boardDao.findBoard(boardDto);
    }
    public void updateBoard(BoardDto boardDto){
        this.boardDao.updateBoard(boardDto);
    }
    public void deleteBoard(BoardDto boardDto){
        this.boardDao.deleteBoard(boardDto);
    }
    public List<CommentDto> findCmt(CommentDto commentDto){
        return boardDao.findCmt(commentDto);
    }
}
