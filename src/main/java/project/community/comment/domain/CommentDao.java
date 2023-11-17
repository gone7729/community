package project.community.comment.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.comment.web.CmtRecDto;
import project.community.comment.web.CommentDto;
import project.community.comment.web.ReplyDto;
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
    public void updateCmt(CommentDto commentDto){this.mapper.updateCmt(commentDto);}
    public void deleteCmt(CommentDto commentDto){this.mapper.deleteCmt(commentDto);}
    public void insertReply(ReplyDto replyDto){this.mapper.insertReply(replyDto);}
    public void updateReply(ReplyDto replyDto){this.mapper.updateReply(replyDto);}
    public void deleteReply(ReplyDto replyDto){this.mapper.deleteReply(replyDto);}
    public void cmtPointUp(CmtRecDto cmtRecDto){this.mapper.cmtPointUp(cmtRecDto);}
    public void cmtPointDown(CmtRecDto cmtRecDto){this.mapper.cmtPointDown(cmtRecDto);}
    public int findCmtRecEmail(String email, int cmt_uid){return mapper.findCmtRecEmail(email, cmt_uid);}
}
