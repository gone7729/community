package project.community.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.community.board.dto.BoardDto;
import project.community.board.service.BoardService;

import java.sql.SQLOutput;



@Controller
@Slf4j
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService =boardService;
    }

    @RequestMapping("do-write")
    public String insert(BoardDto boardDto){

        boardService.insertBoard(boardDto);
        return "redirect:/boardpaging?nowPage=1";
    }

    @RequestMapping("boardpaging")
    public String boardList(Model model,
                            @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                            @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){

        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);
        BoardDto boardDto = new BoardDto();

        boardDto.setOffSet((nowPage - 1) * pageSize);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        System.out.println(boardService.findBoardList(boardDto));
        return "boardpaging";
    }

    @RequestMapping("posting")
    public String posting(Model model, BoardDto boardDto,
                          @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                          @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                          @RequestParam (value = "uid") int uid){
        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);

        boardDto.setUid(uid);
        boardDto.setOffSet((nowPage - 1) * pageSize);

        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        model.addAttribute("posting", boardService.findBoard(boardDto));
        return "posting";
    }

    @RequestMapping("update")
    public String update(Model model, BoardDto boardDto,
                          @RequestParam (value = "uid") int uid){

        boardDto.setUid(uid);

        model.addAttribute("posting", boardService.findBoard(boardDto));
        return "update";
    }

    @PostMapping("postUpdate")
    public String postUpdate(Model model, BoardDto boardDto,
                             @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                             @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                             @RequestParam int uid){

        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);

        boardDto.setUid(uid);
        boardDto.setOffSet((nowPage - 1) * pageSize);

        boardService.updateBoard(boardDto);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        model.addAttribute("posting", boardService.findBoard(boardDto));
        return "posting";
    }

    @GetMapping("/delete")
    public String delete(Model model, BoardDto boardDto,
                         @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                         @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                         @RequestParam ("uid") int uid){

        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);

        boardDto.setUid(uid);
        boardDto.setOffSet((nowPage - 1) * pageSize);

        boardService.deleteBoard(boardDto);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        return "boardpaging";
    }
}
