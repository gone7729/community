package project.community.comment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.board.domain.BoardService;
import project.community.board.web.BoardRecDto;
import project.community.comment.domain.CommentService;
import project.community.user.web.MemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CommentController {
    CommentService commentService;
    BoardService boardService;

    @Autowired
    public CommentController(CommentService commentService, BoardService boardService){
        this.commentService = commentService;
        this.boardService = boardService;
    }

    @PostMapping("cmt-session-check")
    @ResponseBody
    public String cmtSessionCheck(HttpSession session, HttpServletRequest request){
        session = request.getSession(false);
        if (session.getAttribute("user") == null){
            return "false";
        }
        return "true";
    }

    @PostMapping("write-cmt")
    public String writeCmt(@Valid CommentDto commentDto, BindingResult bindingResult,
                           @RequestParam (value = "uid") int uid){
        System.out.println(uid);
        commentDto.setBoard_uid(uid);
        System.out.println("댓글을 작성합니다.");
        if (bindingResult.hasErrors()){
            System.out.println("유효성 검사 실패");
            return "redirect:/posting?uid="+uid;
        }
        commentService.insertComment(commentDto);
        System.out.println("작성된 댓글"+commentDto);
        return  "redirect:/posting?uid=" +uid;
    }

    @PostMapping("write-reply")
    public String writeReply(@Valid ReplyDto replyDto, BindingResult bindingResult,
                           @RequestParam (value = "cmt_uid") int cmtUid,
                             @RequestParam (value = "board_uid") int boardUid){
        replyDto.setCmt_uid(cmtUid);
        System.out.println("댓글을 작성합니다.");
        if (bindingResult.hasErrors()){
            System.out.println("유효성 검사 실패");
            return "redirect:/posting?uid="+boardUid;
        }
        commentService.insertReply(replyDto);
        System.out.println("작성된 댓글"+replyDto);
        return  "redirect:/posting?uid=" +boardUid;
    }

    @PostMapping("update-cmt")
    public String updateCmt(@Valid CommentDto commentDto, BindingResult bindingResult,
                            @RequestParam (value = "board_uid") int uid){
        commentDto.setBoard_uid(uid);
        if (bindingResult.hasErrors()){
            System.out.println("유효성 검사 실패");
            return "redirect:/posting?uid="+commentDto.getBoard_uid();
        }
        commentService.updateCmt(commentDto);
        return "redirect:/posting?uid=" +commentDto.getBoard_uid();
    }

    @PostMapping("update-reply")
    public String updateReply(@Valid ReplyDto replyDto, BindingResult bindingResult,
                            @RequestParam (value = "uid") int uid,
                            @RequestParam (value = "board_uid") int boardUid){
        replyDto.setUid(uid);
        System.out.println(replyDto.getUid());
        if (bindingResult.hasErrors()){
            System.out.println("유효성 검사 실패");
            return "redirect:/posting?uid="+boardUid;
        }
        commentService.updateReply(replyDto);
        return "redirect:/posting?uid=" +boardUid;
    }

    @PostMapping("delete-cmt")
    @ResponseBody
    public String deleteCmt(@RequestBody CommentDto commentDto, HttpServletRequest request, HttpSession session
                            ){

        session = request.getSession(false);
        if (session.getAttribute("user") == null){
            return "로그인 시 이용가능합니다.";
        }
        commentService.deleteCmt(commentDto);
        return "삭제되었습니다.";
    }

    @PostMapping("delete-reply")
    @ResponseBody
    public String deleteReply(@RequestBody ReplyDto replyDto, HttpServletRequest request, HttpSession session
    ){

        session = request.getSession(false);
        if (session.getAttribute("user") == null){
            return "로그인 시 이용가능합니다.";
        }
        commentService.deleteReply(replyDto);
        return "삭제되었습니다.";
    }

    @PostMapping("cmt-up")
    @ResponseBody
    public int cmtUp(HttpSession session, HttpServletRequest request,
                       @RequestBody CmtRecDto cmtRecDto){
        session = request.getSession(false);
        if (session.getAttribute("user") != null){
            MemberDto memberDto = (MemberDto) session.getAttribute("user");
            if(commentService.findCmtRecEmail(memberDto.getEmail(), cmtRecDto.getCmt_uid()) == 0) {
                cmtRecDto.setEmail(memberDto.getEmail());
                commentService.cmtPointUp(cmtRecDto);
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }
    @PostMapping("cmt-down")
    @ResponseBody
    public int cmtDown(HttpSession session, HttpServletRequest request,
                         @RequestBody CmtRecDto cmtRecDto){
        session = request.getSession(false);
        MemberDto memberDto = (MemberDto) session.getAttribute("user");
        if (session.getAttribute("user") != null){
            if(commentService.findCmtRecEmail(memberDto.getEmail(), cmtRecDto.getCmt_uid()) == 0) {
                cmtRecDto.setEmail(memberDto.getEmail());
                commentService.cmtPointDown(cmtRecDto);
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }
}
