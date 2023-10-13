package project.community.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.comment.CommentDto;
import project.community.mybatis.mappers.Mapper;

import java.util.List;

@Repository
public class BoardDao {
    Mapper mapper;

    @Autowired
    public BoardDao(Mapper mapper){
        this.mapper = mapper;
    }

    public List<BoardDto> findAllBoard(){
        return mapper.findAllBoard();
    }

    public void insertBoard(BoardDto boardDto){
        this.mapper.insertBoard(boardDto);
    }

    public int countBoard(){
        return mapper.countBoard();
    }

    public List<BoardDto> findBoardList(BoardDto boardDto) {
        return mapper.findBoardList(boardDto);
    }
    public List<BoardDto> findHomeBoardList(BoardDto boardDto) {
        return mapper.findHomeBoardList(boardDto);
    }
    public BoardDto findBoard(BoardDto boardDto){
        return mapper.findBoard(boardDto);
    }
    public void updateBoard(BoardDto boardDto){
        this.mapper.updateBoard(boardDto);
    }
    public void deleteBoard(BoardDto boardDto){
        this.mapper.deleteBoard(boardDto);
    }
    public List<CommentDto> findCmt(CommentDto commentDto){
        return mapper.findCmt(commentDto);
    }
}
