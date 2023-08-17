package project.community.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.dto.BoardDto;
import project.community.mybatis.mappers.BoardMapper;

import java.util.List;

@Repository
public class BoardDao {
    BoardMapper boardMapper;

    @Autowired
    public BoardDao(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    public void findBoardList(BoardDto boardDto){
        findBoardList(boardDto);
    }
}
