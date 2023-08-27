package project.community.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.community.mybatis.mappers.Mapper;
import project.community.user.dto.MemberDto;

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

    public boolean findEmail(String email){
        return mapper.findEmail();
    }
}
