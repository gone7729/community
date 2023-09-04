package project.community.user.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.community.user.dto.MemberDto;
import project.community.user.service.AuthService;
import project.community.user.service.MemberService;

import javax.jws.soap.SOAPBinding;
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
    public String singIn(Model model, @ModelAttribute MemberDto memberDto,
                         @RequestParam("password") String password, HttpSession session,
                         HttpServletRequest request, RedirectAttributes redirectAttributes) {
        memberDto = authService.singIn(memberDto);
        session = request.getSession();
        System.out.println("찾아온 정보 "+memberDto);
        System.out.println("session 생성 전 "+session);
        System.out.println("입력한 정보가 데이터베이스에 있는가");
        if (memberDto.getEmail() != null && bCryptPasswordEncoder.matches(password, memberDto.getPassword())) {
            System.out.println("있다 그럼 session을 생성한다");
            session.setAttribute("user", memberDto);
            System.out.println(session.getAttribute("user"));
            System.out.println("세션 아이디 " + session.getId());
            return "redirect:/boardpaging";
        } else {
            System.out.println("실패");
            // 로그인 실패 시 메시지를 리다이렉트 속성으로 전달
            redirectAttributes.addFlashAttribute("loginError", "Invalid email or password.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션이 이미 만료되었는지 확인
        if (session != null && session.getAttribute("user") != null) {
            // 세션에서 사용자 정보 삭제
            session.removeAttribute("user");
            // 세션 무효화
            session.invalidate();
            // 로그 메시지
            System.out.println("로그아웃 성공");
        } else {
            // 세션이 이미 만료되었거나 사용자 정보가 없을 경우
            System.out.println("세션이 이미 만료되었거나 사용자 정보가 없습니다.");
        }
        // 리다이렉트 시 메시지 전달을 위한 RedirectAttributes 사용
        redirectAttributes.addFlashAttribute("message", "로그아웃 되었습니다.");
        return "redirect:/login";
    }

    @GetMapping("checkSession")
    public String checkSession(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        System.out.println("세션 확인"+ session.getId());
        if (session != null) {
            MemberDto user = (MemberDto) session.getAttribute("user");
            model.addAttribute("message", "세션 생성됨. 사용자: " + user);
        } else {
            System.out.println("사용자 없");
            model.addAttribute("message", "세션 없음");
        }
        return "redirect:/boardpaging";
    }
}
