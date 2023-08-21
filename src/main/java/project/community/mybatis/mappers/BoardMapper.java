package project.community.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;
import project.community.dto.BoardDto;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto> findAllBoard();

    void insertBoard(BoardDto boardDto);

    int countBoard();

    List<BoardDto> findBoardList(Object obj);
}
