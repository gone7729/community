package project.community.user.dto;

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
    private String nick;
    @NotBlank(message = "공백이 포함될 수 없습니다.")
    @Pattern(regexp = "/^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{6,}$/", message = "비밀번호는 최소 6자리 이상의 영어와 특수문자가 최소 한 개 포함되어야 합니다.")
    private String pw;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "/^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{6,}$/", message = "비밀번호는 최소 6자리 이상의 영어와 특수문자가 최소 한 개 포함되어야 합니다.")
    private String pwCheck;
    private boolean terms;
    private LocalDate createDate;
}
