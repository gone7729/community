package project.community.user.web;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RegisterDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 틀립니다.")
    private String email;
    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nickName;
    @NotBlank(message = "공백이 포함될 수 없습니다.")
    private String password;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String passwordCheck;
    @NotBlank
    private String code;
    private boolean terms;
    private LocalDate createDate;
}
