package project.community.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.user.*;
import project.community.user.domain.Code;
import project.community.user.domain.MemberDao;
import project.community.user.domain.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Controller
public class MemberController {
    MemberService memberService;
    MailManager mailManager;

    @Autowired
    public MemberController(MemberService memberService, MailManager mailManager) {
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
                                 Model model, MemberDto memberDto) {

        if (registerDto.isTerms() == false) {
            bindingResult.rejectValue("terms", "termsError", "이용 약관에 동의해주세요.");
            return "user/account";
        }
        System.out.println("email");
        if (memberService.findEmail(registerDto.getEmail()) == 1) {
            bindingResult.rejectValue("email", "emailErrors", "이미 존재하는 이메일입니다.");
            return "user/account";
        }
        System.out.println("pw");
        if (!registerDto.getPasswordCheck().equals(registerDto.getPassword())) {
            bindingResult.rejectValue("pw", "pwCheckErrors", "비밀번호가 일치하지 않습니다.");
            return "user/account";
        }
        System.out.println("nick");
        if (memberService.findNick(registerDto.getNickName()) == 1) {
            bindingResult.rejectValue("nick", "nickErrors", "이미 존재하는 닉네임입니다.");
            return "user/account";
        }
        System.out.println("전체");
        if (bindingResult.hasErrors()) {
            return "user/account";
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        registerDto.setSalt(Base64.getEncoder().encodeToString(salt));

        memberService.registerMember(registerDto);
        model.addAttribute("memberDto", memberDto);
        return "user/login";
    }

    @PostMapping("emailtest")
    @ResponseBody
    public String emailTest(@RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        System.out.println(registerDto.getEmail());
        String result;

        //json으로 가져온 email로 중복 검사
        if (memberService.findEmail(registerDto.getEmail()) == 1) {
            //fetch로 보낼 메세지
            result = "이미 존재하는 이메일입니다.";
        } else {
            result = "사용 가능합니다.";
        }
        return result;
    }

    @PostMapping("nicktest")
    @ResponseBody
    public String nickTest(@RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        String result;

        if (memberService.findNick(registerDto.getNickName()) == 1) {
            result = "이미 존재하는 닉네임입니다.";
        } else {
            result = "사용 가능합니다.";
        }
        return result;
    }

    @PostMapping("userupdate")
    public String userUpdate(Model model, MemberDto memberDto, HttpSession session) {

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

        if (memberService.findEmail(registerDto.getEmail()) == 1) {
            return "이메일이 중복이거나 형식이 올바르지 않습니다.";
        }

        UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
        String code = uuid.toString().substring(0, 7); // UUID 문자열 중 7자리만 사용하여 인증번호 생성
        String sub = "인증번호 입력을 위한 메일 전송";
        String con = "인증 번호 : " + code;
        mailManager.send(email, sub, con);
        sendAddress.setCode(code);

        memberService.insertCode(sendAddress);

        return "코드가 전송되었습니다.";
    }

    @PostMapping("codecheck")
    @ResponseBody
    public String codeCheck(@RequestBody Map<Object, Object> put, Code codeDto) {
        String code = (String) put.get("code");
        int insertTime = (int) put.get("insertTime");

        if (memberService.findCode(code) == 1) {
            codeDto = memberService.checkCode(code);
            long interval = codeDto.getCodetime() + 180000;

            if(interval < insertTime){
                return "입력시간이 초과되었습니다.";
            }
            return "인증 되었습니다.";
        }
        return "코드가 올바르지 않습니다.";
    }
}
