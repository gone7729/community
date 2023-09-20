package project.community.user.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String singIn(Model model, MemberDto memberDto,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         HttpSession session, HttpServletRequest request) {
        //회원 정보 확인
        memberDto = authService.singIn(memberDto);
        //세션 객체 생성
        session = request.getSession();

        if (memberDto.getEmail() != null && bCryptPasswordEncoder.matches(password, memberDto.getPassword())) {
            //세션 생성
            session.setAttribute("user", memberDto);
            System.out.println(session.getId());
            session.setMaxInactiveInterval(3600);
            model.addAttribute("member", session.getAttribute("user"));
            return "redirect:/index";
        } else {
            System.out.println("실패");
            return "redirect:/login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        System.out.println(session.getId());
        // 세션이 이미 만료되었는지 확인
        if (session.getId() != null) {
            // 세션에서 사용자 정보 삭제
            session.removeAttribute("user");
            // 세션 무효화
            session.invalidate();
            // 로그 메시지
            System.out.println("로그아웃 성공");
        } else {
            System.out.println("세션이 이미 만료되었거나 사용자 정보가 없습니다.");
            // 세션이 이미 만료되었거나 사용자 정보가 없을 경우
        }
        return "login";
    }

}
