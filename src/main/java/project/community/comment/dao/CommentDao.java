package project.community.comment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.comment.dto.CommentDto;
import project.community.mybatis.mappers.Mapper;

@Repository
public class CommentDao {
    Mapper mapper;

    @Autowired
    public CommentDao(Mapper mapper){
        this.mapper = mapper;
    }

    public void insertComment(CommentDto commentDto){
        this.mapper.insertComment(commentDto);
    }
}
