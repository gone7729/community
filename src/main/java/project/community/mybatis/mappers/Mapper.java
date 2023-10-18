package project.community.mybatis.mappers;

import project.community.board.BoardDto;
import project.community.comment.CommentDto;
import project.community.user.domain.Code;
import project.community.user.web.MemberDto;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    //board
    List<BoardDto> findAllBoard();
    void insertBoard(BoardDto boardDto);
    int countBoard();
    List<BoardDto> findBoardList(Object obj);
    List<BoardDto> findHomeBoardList(Object obj);
    BoardDto findBoard(Object obj);
    void updateBoard(Object obj);
    void deleteBoard(Object obj);
    void viewUp(Object obj);

    //user
    void insertMember(Object obj);
    MemberDto memberInfo(MemberDto memberDto);
    int findEmail(String email);
    String findByEmail(String email);
    int findNick(String nick);
    MemberDto singIn(MemberDto memberDto);
    void updateMember(Object obj);

    void insertCode(Object obj);
    Code checkCode(String code);
    int findCode(String code);
    void deleteCode(String code);

    //코맨트
    void insertComment(Object obj);

    List<CommentDto> findCmt(CommentDto commentDto);
}
