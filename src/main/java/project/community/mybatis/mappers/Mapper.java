package project.community.mybatis.mappers;

import project.community.board.dto.BoardDto;
import project.community.comment.dto.CommentDto;
import project.community.user.dto.MemberDto;

import java.lang.reflect.Member;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

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
    MemberDto memberInfo(MemberDto memberDto);
    int findEmail(String email);
    String findByEmail(String email);
    int findNick(String nick);
    MemberDto singIn(MemberDto memberDto);

    //코맨트
    void insertComment(Object obj);

    List<CommentDto> findCmt(CommentDto commentDto);
}
