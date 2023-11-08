package project.community.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.board.web.BoardDto;
import project.community.board.domain.BoardService;
import project.community.board.web.InsertBoard;
import project.community.user.web.MemberDto;

import javax.servlet.http.HttpServletRequest;
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
    public String Home(HttpSession session, HttpServletRequest request,
                       Model model, BoardDto boardDto){
        boardDto.setOffSet(0);
        System.out.println(boardDto);
        List<NewsDto> newsList = homeService.getNews();
        model.addAttribute("news", newsList);
        model.addAttribute("boardPagingList", boardService.findHomeBoardList(boardDto));
        model.addAttribute("member", session.getAttribute("user"));
        System.out.println(homeService.getNews());
        return "index";
    }

    @GetMapping("login")
    public String logIn(@ModelAttribute MemberDto memberDto, HttpSession session, HttpServletRequest request){
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "user/login";
    }

    @RequestMapping("member")
    public String member(HttpSession session, HttpServletRequest request, Model model){
        session = request.getSession(false);
        model.addAttribute("member", session.getAttribute("user"));
        return "user/member";
    }

    @GetMapping("freeboard")
    public String freeBoard(){
        return "boardpaging";
    }

    @GetMapping("write")
    public String write(@ModelAttribute("insertBoard") InsertBoard insertBoard, Model model, HttpSession session, HttpServletRequest request){
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
