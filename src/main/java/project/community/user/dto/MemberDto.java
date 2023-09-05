package project.community.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class MemberDto {
    private String email;
    private String nickName;
    private String password;
    private boolean terms;
    private LocalDate createDate;
    private String role;
}
