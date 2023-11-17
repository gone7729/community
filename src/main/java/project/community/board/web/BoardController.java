package project.community.board.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.board.domain.BoardService;
import project.community.comment.web.CommentDto;
import project.community.comment.web.ReplyDto;
import project.community.user.web.MemberDto;
import project.community.util.Paging;

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
                            @RequestParam(value = "pageSize", defaultValue = "15")int pageSize){

        int total = boardService.countBoard();
        int pageCount = 10;
        Paging paging = new Paging(total, nowPage, pageSize, pageCount);
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
        int pageCount = 10;
        Paging paging = new Paging(total, nowPage, pageSize, pageCount);

        commentDto.setBoard_uid(uid);
        List<CommentDto> commentList = new ArrayList<>();
        List<ReplyDto> replyList = new ArrayList<>();
        if (boardService.findCmt(commentDto) != null){
            commentList = boardService.findCmt(commentDto);
            for (CommentDto comment : commentList) {
                replyDto.setCmt_uid(comment.getUid());
                List<ReplyDto> repliesForComment = boardService.findReply(replyDto);
                replyList.addAll(repliesForComment);
            }
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
                          @RequestBody BoardRecDto boardRecDto){
        session = request.getSession(false);

        if (session.getAttribute("user") != null){
            MemberDto memberDto = (MemberDto) session.getAttribute("user");
            if(boardService.findRecEmail(memberDto.getEmail(), boardRecDto.getBoard_uid()) == 0) {
                boardRecDto.setEmail(memberDto.getEmail());
                boardService.boardPointUp(boardRecDto);
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }
    @PostMapping("point-down")
    @ResponseBody
    public int pointDown(HttpSession session, HttpServletRequest request,
                       @RequestBody BoardRecDto boardRecDto){
        session = request.getSession(false);

        if (session.getAttribute("user") != null){
            MemberDto memberDto = (MemberDto) session.getAttribute("user");
            if(boardService.findRecEmail(memberDto.getEmail(), boardRecDto.getBoard_uid()) == 0) {
                boardRecDto.setEmail(memberDto.getEmail());
                boardService.boardPointDown(boardRecDto);
                return 1;
            } else {
                return 2;
            }
        }
        return 3;
    }
    @GetMapping("search")
    public String search(Model model, HttpSession session, SearchDto searchDto,
                         @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                         @RequestParam(value = "pageSize", defaultValue = "5")int pageSize){
        int total = boardService.searchCount(searchDto.getCtg(), searchDto.getText());
        System.out.println(total);
        int pageCount = 5;
        Paging paging = new Paging(total, nowPage, pageSize, pageCount);
        searchDto.setOffSet((nowPage - 1) * pageSize);
        System.out.println(paging.getStartPage()+","+paging.getLastPage());

        model.addAttribute("searchKey", searchDto);
        model.addAttribute("searchList", boardService.searchBoardList(searchDto));
        model.addAttribute("paging", paging);
        model.addAttribute("member", session.getAttribute("user"));

        return "board/searchpost";
    }

    @RequestMapping("searchpaging")
    public String searchpaging(Model model, HttpSession session, SearchDto searchDto,
                            @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                            @RequestParam(value = "pageSize", defaultValue = "5")int pageSize){

        int total = boardService.searchCount(searchDto.getCtg(), searchDto.getText());
        int pageCount = 5;
        Paging paging = new Paging(total, nowPage, pageSize, pageCount);
        searchDto.setOffSet((nowPage - 1) * pageSize);

        model.addAttribute("searchKey", searchDto);
        model.addAttribute("searchList", boardService.searchBoardList(searchDto));
        model.addAttribute("paging", paging);
        model.addAttribute("member", session.getAttribute("user"));
        return "board/searchpost";
    }
}
