package project.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.dao.BoardDao;
import project.community.dto.BoardDto;

@Service
public class BoardService {

    BoardDao boardDao;

    @Autowired
    public BoardService(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public void findBoardList(BoardDto boardDto){
        boardDao.findBoardList(boardDto);
    }
}
