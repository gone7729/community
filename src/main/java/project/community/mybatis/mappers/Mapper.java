package project.community.mybatis.mappers;

import project.community.board.web.BoardDto;
import project.community.board.web.SearchDto;
import project.community.comment.web.CommentDto;
import project.community.comment.web.ReplyDto;
import project.community.user.domain.Code;
import project.community.user.web.MemberDto;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    //board
    List<BoardDto> findAllBoard();
    void insertBoard(Object obj);
    int countBoard();
    List<BoardDto> findBoardList(Object obj);
    List<BoardDto> findHomeBoardList(Object obj);
    BoardDto findBoard(Object obj);
    void updateBoard(Object obj);
    void deleteBoard(Object obj);
    void viewUp(Object obj);
    void boardPointUp(Object obj);
    void boardPointDown(Object obj);
    int findRecEmail(String email, int board_uid);
    List<SearchDto> searchBoardList(Object obj);
    int searchCount(String ctg, String text);

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
    void deleteUser(String email);

    //코맨트
    void insertComment(Object obj);
    void updateCmt(Object obj);
    void deleteCmt(Object obj);
    List<CommentDto> findCmt(CommentDto commentDto);
    List<ReplyDto> findReply(ReplyDto replyDto);
    void insertReply(Object obj);
    void updateReply(Object obj);
    void deleteReply(Object obj);
    void cmtPointUp(Object obj);
    void cmtPointDown(Object obj);
    int findCmtRecEmail(String email, int cmt_uid);
}
