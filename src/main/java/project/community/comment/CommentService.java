package project.community.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.comment.CommentDao;
import project.community.comment.CommentDto;

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
