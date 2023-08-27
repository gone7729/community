package project.community.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class MemberDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;
    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nick;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String pw;
    private LocalDate createDate;
}
