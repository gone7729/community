package project.community.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.community.user.dto.MemberDto;
import project.community.user.service.AuthService;
import project.community.user.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    AuthService authService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AuthController(AuthService authService){
        this.authService= authService;
    }

    @PostMapping("singIn")
    public String singIn(Model model, @ModelAttribute MemberDto memberDto, @RequestParam("password") String password, HttpSession session, HttpServletRequest request){
        memberDto = authService.singIn(memberDto);
        if (memberDto.getEmail() != null && bCryptPasswordEncoder.matches(password, memberDto.getPassword())){
            session = request.getSession();
            session.setAttribute("user", memberDto.getNickName());

            model.addAttribute("user", session.getAttribute("user"));
            return "index";
        }

        return "redirect:/login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session, HttpServletRequest request){
        session = request.getSession(false);
        if (session != null){
            session.removeAttribute("user");
            session.invalidate();
        }
        return "redirect:/login";
    }
}
