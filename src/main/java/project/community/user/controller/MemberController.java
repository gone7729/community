package project.community.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.user.dto.RegisterDto;
import project.community.user.dto.MemberDto;
import project.community.user.service.AuthService;
import project.community.user.service.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MemberController {
    MemberService memberService;
    AuthService authService;


    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }



    @RequestMapping("/user/info")
    public String goInfo(){
        return "member";
    }

    @RequestMapping("account")
    public String accountPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "/user/account";
    }

    @PostMapping("accountValidTest")
    public String registerMember(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult bindingResult){

        if(registerDto.isTerms()==false){
            bindingResult.rejectValue("terms", "termsError", "이용 약관에 동의해주세요.");
            return "user/account";
        }

        if (memberService.findEmail(registerDto.getEmail())==1){
            bindingResult.rejectValue("email","emailErrors","이미 존재하는 이메일입니다.");
            return "user/account";
        }

        if (!registerDto.getPasswordCheck().equals(registerDto.getPassword())){
            bindingResult.rejectValue("pw", "pwCheckErrors", "비밀번호가 일치하지 않습니다.");
            return "user/account";
        }

        if (memberService.findNick(registerDto.getNickName())==1){
            bindingResult.rejectValue("nick", "nickErrors", "이미 존재하는 닉네임입니다.");
            return "user/account";
        }

        if (bindingResult.hasErrors()){
            return "user/account";
        }

        memberService.registerMember(registerDto);
        return "user/login";
    }

    @PostMapping("emailTest")
    @ResponseBody
    public String emailTest(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult){
        System.out.println(registerDto.getEmail());
        String result;

        //json으로 가져온 email로 중복 검사
        if(memberService.findEmail(registerDto.getEmail())==1){
            //fetch로 보낼 메세지
            result="이미 존재하는 이메일입니다.";
        } else {
            result="사용 가능합니다.";
        }
        return result;
    }

    @PostMapping("nickTest")
    @ResponseBody
    public String nickTest(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult){
        String result;

        if(memberService.findNick(registerDto.getNickName())==1){
            result="이미 존재하는 닉네임입니다.";
        } else {
            result="사용 가능합니다.";
        }
        return result;
    }

    @PostMapping("userUpdate")
    public String userUpdate(Model model, MemberDto memberDto, HttpSession session){

        memberService.updateMember(memberDto);
        memberDto = memberService.memberInfo(memberDto);
        session.setAttribute("user", memberDto);
        model.addAttribute("member", session.getAttribute("user"));
        return "user/member";
    }
}
