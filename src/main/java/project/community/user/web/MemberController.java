package project.community.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.user.*;
import project.community.user.domain.AuthService;
import project.community.user.domain.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Controller
public class MemberController {
    MemberService memberService;
    MailManager mailManager;

    @Autowired
    public MemberController(MemberService memberService, MailManager mailManager){
        this.memberService = memberService;
        this.mailManager = mailManager;
    }

    @RequestMapping("account")
    public String accountPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "user/account";
    }

    @PostMapping("newaccount")
    public String registerMember(@Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult bindingResult,
                                 Model model, MemberDto memberDto){
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
        model.addAttribute("memberDto", memberDto);
        return "user/login";
    }

    @PostMapping("emailtest")
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

    @PostMapping("nicktest")
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

    @PostMapping("userupdate")
    public String userUpdate(Model model, MemberDto memberDto, HttpSession session){

        memberService.updateMember(memberDto);
        memberDto = memberService.memberInfo(memberDto);
        session.setAttribute("user", memberDto);
        model.addAttribute("member", session.getAttribute("user"));
        return "user/member";
    }

    //email인증
    @PostMapping("sendmail")
    @ResponseBody  //AJAX후 값을 리턴하기위해 작성
    public String SendMail(@RequestBody SendAddress sendAddress, RegisterDto registerDto) throws Exception {

        String email = sendAddress.getEmail();
        registerDto.setEmail(email);

        if (memberService.findEmail(registerDto.getEmail())==1){
            return "";
        }

        System.out.println("이메일 전송 시작 전송할 이메일은 : " + email);
        UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
        String key = uuid.toString().substring(0, 7); // UUID 문자열 중 7자리만 사용하여 인증번호 생성
        String sub ="인증번호 입력을 위한 메일 전송";
        String con = "인증 번호 : "+key;
        mailManager.send(email,sub,con);
        sendAddress.setKey(key);


        System.out.println("key: " + sendAddress.getKey());
        System.out.println("발송 시간: " + sendAddress.getRegdate());
        return key;
    }

    @PostMapping("checkkey")
    @ResponseBody
    public boolean CheckKey(String key, String insertKey,String email) throws Exception {

        if(key.equals(insertKey)) {
            return true;
        }
        return false;
    }
}
