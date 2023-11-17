package project.community.comment.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.comment.web.CmtRecDto;
import project.community.comment.web.CommentDto;
import project.community.comment.web.ReplyDto;

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
    public void cmtPointUp(CmtRecDto cmtRecDto){this.commentDao.cmtPointUp(cmtRecDto);}
    public void cmtPointDown(CmtRecDto cmtRecDto){this.commentDao.cmtPointDown(cmtRecDto);}
    public int findCmtRecEmail(String email, int cmt_uid){return commentDao.findCmtRecEmail(email, cmt_uid);}

}
