package project.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.dto.BoardDto;
import project.community.service.BoardService;

@Controller
public class HomeController {

    BoardService boardService;

    @Autowired
    public HomeController(BoardService boardService){
        this.boardService = boardService;
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("account")
    public String NewAccount(){
        return "account";
    }

    @RequestMapping("member")
    public String member(){
        return "member";
    }

    @GetMapping("freeboard")
    public String freeBoard(){
        return "freeboard";
    }

    @RequestMapping("write")
    public String write(){
        return "write";
    }
}
