package project.community.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.community.user.MemberDao;
import project.community.user.MemberDto;

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
