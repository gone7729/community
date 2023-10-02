package project.community.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.mybatis.mappers.Mapper;
import project.community.user.MemberDto;

@Repository
public class MemberDao {
    Mapper mapper;

    @Autowired
    public MemberDao(Mapper mapper){
        this.mapper = mapper;
    }
    public void insertMember(MemberDto memberDto){
        this.mapper.insertMember(memberDto);
    }
    public MemberDto memberInfo(MemberDto memberDto){
        return mapper.memberInfo(memberDto);
    }

    public int findEmail(String email) {
        return mapper.findEmail(email);
    }
    public String findByEmail(String email) {
        return mapper.findByEmail(email);
    }
    public int findNick(String nick){
        return mapper.findNick(nick);
    }
    public MemberDto singIn(MemberDto memberDto){
        return mapper.singIn(memberDto);
    }
    public void updateMember(MemberDto memberDto){
        this.mapper.updateMember(memberDto);
    }
}
