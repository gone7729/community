package project.community.board.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.board.domain.BoardService;
import project.community.comment.CommentDto;
import project.community.comment.ReplyDto;
import project.community.user.web.MemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService =boardService;
    }

    @PostMapping("write")
    public String insert(@Valid InsertBoard insertBoard, BindingResult bindingResult,
                         Model model, HttpSession session){
        model.addAttribute("member", session.getAttribute("user"));

        if (bindingResult.hasErrors()) {
            System.out.println("공란 발생");
            return "redirect:/write";
        }
        boardService.insertBoard(insertBoard);
        return "redirect:/boardpaging?nowPage=1";
    }

    @RequestMapping("boardpaging")
    public String boardList(Model model, HttpSession session, BoardDto boardDto,
                            @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                            @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){

        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);
        boardDto.setOffSet((nowPage - 1) * pageSize);

        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        model.addAttribute("member", session.getAttribute("user"));
        return "board/boardpaging";
    }

    @RequestMapping("posting")
    public String posting(Model model, BoardDto boardDto, CommentDto commentDto, ReplyDto replyDto,
                          HttpSession session, HttpServletRequest httpRequest,
                          @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                          @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                          @RequestParam (value = "uid") int uid){
        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);

        commentDto.setBoard_uid(uid);
        List<CommentDto> commentList = boardService.findCmt(commentDto);
        List<ReplyDto> replyList = new ArrayList<>();
        for (CommentDto comment : commentList) {
            replyDto.setCmt_uid(comment.getUid());
            List<ReplyDto> repliesForComment = boardService.findReply(replyDto);
            replyList.addAll(repliesForComment);
        }

        boardDto.setUid(uid);
        boardDto.setOffSet((nowPage - 1) * pageSize);

        session = httpRequest.getSession(false);

        Long accessTime = (Long) session.getAttribute("accessTime");
        long overTime = System.currentTimeMillis();

        if (accessTime == null|| (overTime - accessTime) >= 300000){
            boardService.viewUp(uid);
            session.setAttribute("accessTime", System.currentTimeMillis());
        }
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        model.addAttribute("posting", boardService.findBoard(boardDto));
        model.addAttribute("cmt", commentList);
        model.addAttribute("reply", replyList);
        model.addAttribute("member", session.getAttribute("user"));
        return "board/posting";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("updateBoard") UpdateBoard updateBoard, Model model,
                         BoardDto boardDto, HttpSession session,
                          @RequestParam (value = "uid") int uid){

        boardDto.setUid(uid);

        model.addAttribute("member", session.getAttribute("user"));
        model.addAttribute("posting", boardService.findBoard(boardDto));
        return "board/update";
    }

    @PostMapping("postUpdate")
    public String postUpdate(@Valid UpdateBoard updateBoard, BindingResult bindingResult, Model model, HttpSession session){
        model.addAttribute("member", session.getAttribute("user"));
        System.out.println(updateBoard);
        if (bindingResult.hasErrors()) {
            System.out.println("공란 발생");
            return "redirect:/update?uid=" + updateBoard.getUid();
        }
        boardService.updateBoard(updateBoard);
        return "redirect:/posting?uid=" +updateBoard.getUid();
    }

    @GetMapping("delete")
    public String delete(BoardDto boardDto, Principal principal,
                         @RequestParam ("uid") int uid){
        boardDto.setUid(uid);

        boardService.deleteBoard(boardDto);
        return "redirect:/boardpaging?nowPage=1";
    }

    @PostMapping("point-up")
    @ResponseBody
    public int pointUp(HttpSession session, HttpServletRequest request,
                          @RequestBody RecDto recDto){
        session = request.getSession(false);
        MemberDto memberDto = (MemberDto) session.getAttribute("user");
        if (session.getAttribute("user") != null){
            if(boardService.findRecEmail(memberDto.getEmail()) == 0) {
                recDto.setEmail(memberDto.getEmail());
                boardService.pointUp(recDto);
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }
}
