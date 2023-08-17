package project.community.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;
import project.community.dto.BoardDto;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto> findBoardList();
}
