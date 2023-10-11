package project.community.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.user.domain.AuthService;
import project.community.user.domain.MemberService;

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

    @PostMapping("login")
    public String singIn(@Valid @ModelAttribute("memberDto") MemberDto memberDto,
                         BindingResult bindingResult, Model model,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         HttpSession session, HttpServletRequest request) {

        memberDto = authService.singIn(memberDto);
        String previousUrl = (String) session.getAttribute("previousUrl");
        System.out.println(previousUrl);

        if (memberService.findEmail(email) == 0) {
            bindingResult.reject("loginEmail",  "이메일 혹은 비밀번호를 틀렸습니다.");
            return "user/login";
        }
        if (bCryptPasswordEncoder.matches(password, memberDto.getPassword()) != true) {
            bindingResult.reject("loginPassword",  "이메일 혹은 비밀번호를 틀렸습니다.");
            return "user/login";
        }

        session = request.getSession();
        System.out.println("로그인 성공" + session);
        session.setAttribute("user", memberDto);
        session.setMaxInactiveInterval(3600);
        model.addAttribute("member", session.getAttribute("user"));

        if (previousUrl == null){
            System.out.println("이전 페이지가 없으므로 메인페이지로");
            return "redirect:/";
        }
        return "redirect:" + previousUrl;
    }

    @GetMapping("logout")
    public String logout(HttpSession session, HttpServletRequest request,
                         @ModelAttribute MemberDto memberDto) {

        System.out.println(session.getAttribute("user"));
        // 세션이 이미 만료되었는지 확인
        if (session != null) {
            // 세션에서 사용자 정보 삭제
            session.removeAttribute("user");
            // 세션 무효화
            session.invalidate();
            // 로그 메시지
        } else {
            System.out.println("세션이 이미 만료되었거나 사용자 정보가 없습니다.");
            // 세션이 이미 만료되었거나 사용자 정보가 없을 경우
        }
        System.out.println("로그아웃 성공: "+session);
        return "redirect:/login";
    }

}
