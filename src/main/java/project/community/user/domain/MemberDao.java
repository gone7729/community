package project.community.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.mybatis.mappers.Mapper;
import project.community.user.web.MemberDto;

@Repository
public class MemberDao {
    Mapper mapper;

    @Autowired
    public MemberDao(Mapper mapper){
        this.mapper = mapper;
    }
    public void insertMember(Member member){
        this.mapper.insertMember(member);
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
    public void insertCode(Code code){
        this.mapper.insertCode(code);
    }
    public Code checkCode(String code){return mapper.checkCode(code);}
    public int findCode(String code){return mapper.findCode(code);}
    public void deleteCode(String code){
        this.mapper.deleteCode(code);
    }
}
