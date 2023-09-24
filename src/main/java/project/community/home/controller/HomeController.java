package project.community.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.board.dto.BoardDto;
import project.community.board.service.BoardService;
import project.community.home.service.HomeService;
import project.community.user.dto.MemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    HomeService homeService;
    BoardService boardService;

    @Autowired
    public HomeController(HomeService homeService, BoardService boardService){
        this.homeService = homeService;
        this.boardService = boardService;
    }

    @RequestMapping("index")
    public String Home(HttpSession session, Model model, BoardDto boardDto){
        boardDto.setOffSet(0);
        System.out.println(boardDto);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("member", session.getAttribute("user"));
        return "index";
    }
    @GetMapping("login")
    public String logIn(@ModelAttribute MemberDto memberDto){
        return "/user/login";
    }


    @RequestMapping("member")
    public String member(HttpSession session, Model model){
        model.addAttribute("member", session.getAttribute("user"));
        return "/user/member";
    }

    @GetMapping("freeboard")
    public String freeBoard(){
        return "boardpaging";
    }

    @GetMapping("write")
    public String write(Model model, HttpSession session){
        model.addAttribute("member", session.getAttribute("user"));
        return "board/write";
    }

    @RequestMapping("post")
    public String post(){
        return "post";
    }

    @RequestMapping("searchpost")
    public String searchPost(){
        return "searchpost";
    }
}
