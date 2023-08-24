package project.community.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    private String email;
    private String nick;
    private String pw;
    private LocalDate createDate;
}
