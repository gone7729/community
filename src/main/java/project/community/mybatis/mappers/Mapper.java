package project.community.mybatis.mappers;

import project.community.board.dto.BoardDto;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    //board
    List<BoardDto> findAllBoard();
    void insertBoard(BoardDto boardDto);
    int countBoard();
    List<BoardDto> findBoardList(Object obj);
    //user
}
