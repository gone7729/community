package project.community.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.community.comment.CommentDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@Slf4j
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService =boardService;
    }

    @PostMapping("write")
    public String insert(BoardDto boardDto){

        boardService.insertBoard(boardDto);
        return "redirect:/boardpaging?nowPage=1";
    }

    @RequestMapping("boardpaging")
    public String boardList(Model model, HttpSession session,
                            @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                            @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){

        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);
        BoardDto boardDto = new BoardDto();

        boardDto.setOffSet((nowPage - 1) * pageSize);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        model.addAttribute("member", session.getAttribute("user"));
        return "board/boardpaging";
    }

    @RequestMapping("posting")
    public String posting(Model model, BoardDto boardDto, CommentDto commentDto, HttpSession session,
                          @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                          @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                          @RequestParam (value = "uid") int uid){
        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);

        commentDto.setBoard_uid(uid);
        boardDto.setUid(uid);
        boardDto.setOffSet((nowPage - 1) * pageSize);

        boardService.viewUp(uid);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        model.addAttribute("posting", boardService.findBoard(boardDto));
        model.addAttribute("cmt", boardService.findCmt(commentDto));
        model.addAttribute("member", session.getAttribute("user"));
        return "board/posting";
    }

    @RequestMapping("update")
    public String update(Model model, BoardDto boardDto,
                          @RequestParam (value = "uid") int uid){

        boardDto.setUid(uid);

        model.addAttribute("posting", boardService.findBoard(boardDto));
        return "board/update";
    }

    @PostMapping("postUpdate")
    public String postUpdate(BoardDto boardDto){

        boardService.updateBoard(boardDto);
        return "redirect:/posting?uid=" +boardDto.getUid();
    }

    @GetMapping("delete")
    public String delete(BoardDto boardDto, Principal principal,
                         @RequestParam ("uid") int uid){
        boardDto.setUid(uid);

        boardService.deleteBoard(boardDto);
        return "redirect:/boardpaging?nowPage=1";
    }
}
