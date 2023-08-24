package project.community.mybatis.mappers;

import project.community.board.dto.BoardDto;
import project.community.user.dto.MemberDto;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    //board
    List<BoardDto> findAllBoard();
    void insertBoard(BoardDto boardDto);
    int countBoard();
    List<BoardDto> findBoardList(Object obj);
    BoardDto findBoard(Object obj);
    void updateBoard(Object obj);
    void deleteBoard(Object obj);
    //user
    void insertMember(MemberDto memberDto);
}
