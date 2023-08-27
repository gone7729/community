package project.community.user.service;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.community.user.dao.MemberDao;
import project.community.user.dto.MemberDto;

@Service
public class MemberService {
    MemberDao memberDao;

    @Autowired
    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void insertMember(MemberDto memberDto){
        this.memberDao.insertMember(memberDto);
    }
    public MemberDto memberInfo(MemberDto memberDto){
        return memberDao.memberInfo(memberDto);
    }
    public boolean findEmail(String email) {
        String result="";
        result = memberDao.findEmail(email)
    }
    public void registerMember(MemberDto memberDto) {

        memberDto.setEmail(memberDto.getEmail());
        memberDto.setNick(memberDto.getNick());
        memberDto.setPw(memberDto.getPw());

        memberDao.insertMember(memberDto);
    }
}
