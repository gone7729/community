package project.community.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.board.dto.ListSetDto;
import project.community.board.dto.BoardDto;
import project.community.board.service.BoardService;


@Controller
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService =boardService;
    }

    @RequestMapping("write")
    public String insert(BoardDto boardDto){

        boardService.insertBoard(boardDto);
        return "redirect:/boardpaging?nowPage=1";
    }

    @RequestMapping("boardpaging")
    public String boardList(Model model, BoardDto boardDto, ListSetDto listSetDto,
                            @RequestParam(value = "nowPage", defaultValue = "1")int nowPage,
                            @RequestParam(value = "pageSize", defaultValue = "15")int pageSize){

        int total = boardService.countBoard();
        project.community.util.Paging paging = new project.community.util.Paging(total, nowPage, pageSize);


        listSetDto.setOffSet((nowPage - 1) * pageSize);

        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("paging", paging);
        return "boardPaging";
    }
}
