package project.community.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.community.user.MemberDao;
import project.community.user.RegisterDto;
import project.community.user.MemberDto;

@Service
public class MemberService {
    MemberDao memberDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public int findEmail(String email) {
        return memberDao.findEmail(email);
    }
    public int findNick(String nick) {
        return memberDao.findNick(nick);
    }
    public void registerMember(RegisterDto registerDto){
        MemberDto memberDto = new MemberDto();

        memberDto.setEmail(registerDto.getEmail());
        memberDto.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        memberDto.setNickName(registerDto.getNickName());
        memberDto.setTerms(registerDto.isTerms());

        this.memberDao.insertMember(memberDto);
    }
    public void updateMember(MemberDto memberDto){
        this.memberDao.updateMember(memberDto);
    }

}
