package project.community.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.board.dto.BoardDto;
import project.community.mybatis.mappers.BoardMapper;

import java.util.List;

@Repository
public class BoardDao {
    BoardMapper boardMapper;

    @Autowired
    public BoardDao(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    public List<BoardDto> findAllBoard(){
        return boardMapper.findAllBoard();
    }

    public void insertBoard(BoardDto boardDto){
        boardMapper.insertBoard(boardDto);
    }

    public int countBoard(){
        return boardMapper.countBoard();
    }

    public List<BoardDto> findBoardList(BoardDto boardDto){
        return boardMapper.findBoardList(boardDto);
    }
}
