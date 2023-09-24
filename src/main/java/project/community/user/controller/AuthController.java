package project.community.user.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.community.user.dto.MemberDto;
import project.community.user.service.AuthService;
import project.community.user.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {
    AuthService authService;
    MemberService memberService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AuthController(AuthService authService, MemberService memberService){
        this.authService= authService;
        this.memberService = memberService;
    }

    @PostMapping("singIn")
    public String singIn(@Valid @ModelAttribute("memberDto") MemberDto memberDto,
                         BindingResult bindingResult, Model model,
                         @RequestParam(defaultValue = "/index")String redirectURL,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         HttpSession session, HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        memberDto = authService.singIn(memberDto);

        if (memberService.findEmail(email) == 0) {
            bindingResult.reject("loginEmail",  "이메일 혹은 비밀번호를 틀렸습니다.");
            return "user/login";
        }
        if (bCryptPasswordEncoder.matches(password, memberDto.getPassword()) != true) {
            bindingResult.reject("loginPassword",  "이메일 혹은 비밀번호를 틀렸습니다.");
            return "user/login";
        }
        System.out.println("로그인 성공");
        session = request.getSession();
        session.setAttribute("user", memberDto);
        session.setMaxInactiveInterval(3600);
        model.addAttribute("member", session.getAttribute("user"));
        redirectAttributes.addFlashAttribute("msg","로그인 성공");
        return "redirect:" + redirectURL;
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
