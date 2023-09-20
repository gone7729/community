package project.community.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.community.comment.dto.CommentDto;
import project.community.comment.service.CommentService;
import project.community.user.dto.MemberDto;

@Controller
public class CommentController {
    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("write-cmt")
    public String writeCmt(CommentDto commentDto, MemberDto memberDto,
                           @RequestParam (value = "uid") int uid){
        commentDto.setBoard_uid(uid);
        commentService.insertComment(commentDto);
        System.out.println(commentDto);
        return  "redirect:/posting?uid=" +commentDto.getBoard_uid();
    }
}
