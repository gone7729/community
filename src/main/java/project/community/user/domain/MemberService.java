package project.community.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.community.user.web.MemberDto;
import project.community.user.web.RegisterDto;
import project.community.user.web.SendAddress;

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
        member.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()+registerDto.getSalt()));
        System.out.println(bCryptPasswordEncoder.encode(registerDto.getPassword()+registerDto.getSalt()));
        member.setNickName(registerDto.getNickName());
        member.setSalt(registerDto.getSalt());

        this.memberDao.insertMember(member);
        this.memberDao.deleteCode(registerDto.getCode());
    }
    public void updateMember(MemberDto memberDto){
        this.memberDao.updateMember(memberDto);
    }
    public void insertCode(SendAddress sendAddress){
        Code code = new Code();
        code.setCode(sendAddress.getCode());
        code.setCodetime(sendAddress.getCodetime());
        this.memberDao.insertCode(code);
    }
    public Code checkCode(String code){
        return memberDao.checkCode(code);
    }
    public int findCode(String code){return memberDao.findCode(code);}
}
