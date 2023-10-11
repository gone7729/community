package project.community.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.user.web.MemberDto;

@Service
public class AuthService {
    MemberDao memberDao;

    @Autowired
    public AuthService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public MemberDto singIn(MemberDto memberDto) {
        return memberDao.singIn(memberDto);
    }


}
