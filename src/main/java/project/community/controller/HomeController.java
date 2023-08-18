package project.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.service.BoardService;

@Controller
public class HomeController {

    BoardService boardService;

    @RequestMapping("login")
    public String login(){

        return "login";
    }
    @RequestMapping("account")
    public String NewAccount(){
        return "account";
    }

    @GetMapping("freeboard")
    public String freeBoard(){
        return "freeboard";
    }

    @RequestMapping("member")
    public String member(){
        return "member";
    }
}
