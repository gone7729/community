package project.community.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.board.BoardDto;
import project.community.board.BoardService;
import project.community.user.MemberDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    HomeService homeService;
    BoardService boardService;

    @Autowired
    public HomeController(HomeService homeService, BoardService boardService){
        this.homeService = homeService;
        this.boardService = boardService;
    }


    @GetMapping("/")
    public String Home(HttpSession session, Model model, BoardDto boardDto){
        boardDto.setOffSet(0);
        System.out.println(boardDto);
        List<NewsDto> newsList = homeService.getNews();
        model.addAttribute("news", newsList);
        model.addAttribute("boardPagingList", boardService.findBoardList(boardDto));
        model.addAttribute("member", session.getAttribute("user"));
        System.out.println(homeService.getNews());
        return "index";
    }

    @GetMapping("login")
    public String logIn(@ModelAttribute MemberDto memberDto){
        return "user/login";
    }

    @RequestMapping("member")
    public String member(HttpSession session, Model model){
        model.addAttribute("member", session.getAttribute("user"));
        return "user/member";
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
