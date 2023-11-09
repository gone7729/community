package project.community.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.board.domain.BoardService;
import project.community.board.web.BoardController;
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
    public void updateCmt(CommentDto commentDto){
        this.commentDao.updateCmt(commentDto);
    }
    public void deleteCmt(CommentDto commentDto){
        this.commentDao.deleteCmt(commentDto);
    }
    public void insertReply(ReplyDto replyDto){
        this.commentDao.insertReply(replyDto);
    }
    public void updateReply(ReplyDto replyDto){
        this.commentDao.updateReply(replyDto);
    }
    public void deleteReply(ReplyDto replyDto){
        this.commentDao.deleteReply(replyDto);
    }


}
