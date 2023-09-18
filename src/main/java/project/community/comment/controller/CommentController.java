package project.community.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.comment.dto.CommentDto;
import project.community.comment.service.CommentService;

@Controller
public class CommentController {
    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @RequestMapping("write-cmt")
    public String writeCmt(CommentDto commentDto){
        commentService.insertComment(commentDto);
        return  "posting?uid=" + commentDto.getBoard_uid();
    }
}
