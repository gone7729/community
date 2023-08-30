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
        model.addAttribute("registerDto", new RegisterDto());
        return "/user/account";
    }
    @PostMapping("accountValid")
    public String registerUser(@Valid @ModelAttribute("memberDto") MemberDto memberDto, BindingResult bindingResult) {

        //이용 약관 확인
        if(memberDto.isTerms() == false){
            bindingResult.rejectValue("terms","termsErrors","이용 약관에 동의해주세요.");
            return "user/account";
        }
        // 이메일 중복 확인
        if (memberService.findEmail(memberDto.getEmail())==1) {
            bindingResult.rejectValue("email","duplicationEmail","이미 존재하는 이메일입니다.");
            //bindingResult.reject("duplicationEmail","에러입니다.");
            return "user/account";
        }
        //비밀번호 일치 확인
        if(memberDto.getPw()!=memberDto.getPwCheck()){
            bindingResult.rejectValue("pw","pwErrors","비밀번호가 일치하지 않습니다.");
            return "user/account";
        }
        //닉네임 중복 확인
        if(memberService.findNick(memberDto.getNick())==1){
            bindingResult.rejectValue("nick","duplicationNick","이미 존재하는 닉네임입니다.");
            return "user/account";
        }
        //공백 처리
        if (bindingResult.hasErrors()) {
            return "user/account";
        }

       // 회원 가입 처리
        memberService.insertMember(memberDto);

        return "user/login"; // 회원 가입 완료 후 로그인 페이지로 이동
    }

    @PostMapping("accountValidTest")
    public String registerMember(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult bindingResult){
        System.out.println(registerDto.getPwCheck()+"비번체크");
        System.out.println(registerDto.getPw()+"비번");

        if(registerDto.isTerms()==false){
            bindingResult.rejectValue("terms", "termsError", "이용 약관에 동의해주세요.");
            return "user/account";
        }

        if (memberService.findEmail(registerDto.getEmail())==1){
            bindingResult.rejectValue("email","emailErrors","이미 존재하는 이메일입니다.");
            return "user/account";
        }

        if (!registerDto.getPwCheck().equals(registerDto.getPw())){
            bindingResult.rejectValue("pw", "pwCheckErrors", "비밀번호가 일치하지 않습니다.");
            bindingResult.rejectValue("pwCheck", "pwCheckErrors", "비밀번호가 일치하지 않습니다.");
            return "user/account";
        }

        if (memberService.findNick(registerDto.getNick())==1){
            bindingResult.rejectValue("nick", "nickErrors", "이미 존재하는 닉네임입니다.");
            return "user/account";
        }

        if (bindingResult.hasErrors()){
            return "user/account";
        }

        memberService.registerMember(registerDto);
        return "user/login";
    }

    @PostMapping("test")
    @ResponseBody
    public String test(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult){
        String result;

        //json으로 가져온 email로 중복 검사
        if(memberService.findEmail(registerDto.getEmail())==1){
            //fetch로 보낼 메세지
            return result="이미 존재하는 이메일입니다.";
        }
            return result="사용 가능합니다.";
    }
}
