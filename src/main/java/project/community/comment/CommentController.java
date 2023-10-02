package project.community.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.community.user.MemberDto;

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
