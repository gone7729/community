package project.community.user.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class Member {

    private String email;
    private String nickName;
    private String password;
    private boolean terms;
    private LocalDate createDate;

}
