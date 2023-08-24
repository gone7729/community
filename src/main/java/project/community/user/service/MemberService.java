package project.community.user.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
