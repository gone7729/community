package project.community.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.comment.dao.CommentDao;
import project.community.comment.dto.CommentDto;

@Service
public class CommentService {
    CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public void insertComment(CommentDto commentDto){
        this.commentDao.insertComment(commentDto);
    }
}
