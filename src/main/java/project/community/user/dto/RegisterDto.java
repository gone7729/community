package project.community.user.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class RegisterDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "")
    private String email;
    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nick;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String pw;
    @NotBlank(message = "비밀번호가 일치하지 않습니다.")
    private String pwCheck;
    private boolean terms;
    private LocalDate createDate;
}