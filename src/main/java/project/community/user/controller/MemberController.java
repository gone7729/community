package project.community.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.mybatis.mappers.Mapper;
import project.community.user.dto.MemberDto;
import project.community.user.service.MemberService;

import javax.validation.Valid;

@Controller
public class MemberController {
    MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }



    @RequestMapping("/user/info")
    public String goInfo(){
        return "member";
    }

    @GetMapping("account")
    public String accountPage(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/user/account";
    }
    @PostMapping("newAccount")
    public String registerUser(@Valid @ModelAttribute("memberDto") MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/account";
        }

        // 중복 데이터 처리
        if (memberService.findEmail(memberDto.getEmail())) {
            bindingResult.rejectValue("email", "error.memberDto", "이미 등록된 이메일 주소입니다.");
            return "user/account";
        }

        // 회원 가입 처리
        memberService.registerMember(memberDto);

        return "redirect:/user/login"; // 회원 가입 완료 후 로그인 페이지로 이동
    }
}
