package project.community.user.web;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MemberDto {

    @NotBlank(message = "이메일 혹은 비밀번호를 틀렸습니다")
    private String email;
    private String nickName;
    @NotBlank(message = "이메일 혹은 비밀번호를 틀렸습니다")
    private String password;
    private boolean terms;
    private LocalDate createDate;
    private String role;
}
