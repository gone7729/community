package project.community.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.community.user.dao.MemberDao;
import project.community.user.dto.MemberDto;

@Service
public class AuthService {
    MemberDao memberDao;

    @Autowired
    public AuthService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public MemberDto singIn(MemberDto memberDto){
        return memberDao.singIn(memberDto);
    }
}
