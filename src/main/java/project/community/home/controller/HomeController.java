package project.community.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.community.board.dto.BoardDto;
import project.community.home.service.HomeService;

@Controller
public class HomeController {

    HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
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
        return "boardpaging";
    }

    @RequestMapping("go-write")
    public String write(){
        return "write";
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
