package project.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.service.BoardService;

@Controller
public class HomeController {

    BoardService boardService;

    @RequestMapping("/login")
    public String login(){

        return "login";
    }
}
