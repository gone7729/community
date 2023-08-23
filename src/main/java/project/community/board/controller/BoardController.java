package project.community.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.board.dto.BoardDto;
import project.community.board.service.BoardService;


@Controller
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService =boardService;
    }

    @RequestMapping("do-write")
    public String insert(@ModelAttribute BoardDto boardDto){

        boardService.insertBoard(boardDto);
        return "write";
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
    public String posting(Model model,
                          @RequestParam (value = "uid") int uid){
        BoardDto boardDto = new BoardDto();
        boardDto.setUid(uid);

        model.addAttribute("posting", boardService.findBoard(boardDto));
        System.out.println(boardService.findBoard(boardDto));
        return "posting";
    }
}
