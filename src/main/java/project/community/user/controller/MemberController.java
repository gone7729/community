package project.community.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.mybatis.mappers.Mapper;
import project.community.user.dto.MemberDto;
import project.community.user.service.MemberService;

@Controller
public class MemberController {
    MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @RequestMapping("newAccount")
    public String newAccount(Model model, MemberDto memberDto){

        memberService.insertMember(memberDto);
        return "index";
    }

}
