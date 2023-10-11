package project.community.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.community.user.web.MemberDto;
import project.community.user.web.RegisterDto;

@Service
public class MemberService {
    MemberDao memberDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void insertMember(Member member){
        this.memberDao.insertMember(member);
    }
    public MemberDto memberInfo(MemberDto memberDto){
        return memberDao.memberInfo(memberDto);
    }
    public int findEmail(String email) {
        return memberDao.findEmail(email);
    }
    public int findNick(String nick) {
        return memberDao.findNick(nick);
    }
    public void registerMember(RegisterDto registerDto){
        Member member = new Member();

        member.setEmail(registerDto.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        member.setNickName(registerDto.getNickName());
        member.setTerms(registerDto.isTerms());

        this.memberDao.insertMember(member);
    }
    public void updateMember(MemberDto memberDto){
        this.memberDao.updateMember(memberDto);
    }

}
