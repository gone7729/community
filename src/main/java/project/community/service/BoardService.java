package project.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.dao.BoardDao;
import project.community.dto.BoardDto;

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
}
