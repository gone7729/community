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

    @GetMapping("login")
    public String logIn(){
        return "/user/login";
    }


    @RequestMapping("/user/member")
    public String member(){
        return "/user/member";
    }

    @GetMapping("/public/freeboard")
    public String freeBoard(){
        return "boardpaging";
    }

    @RequestMapping("write")
    public String write(){
        return "/board/write";
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
