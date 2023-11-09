package project.community.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private int uid;
    private int cmt_uid;
    private String writer;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private int up_point;
    private int down_point;
    private LocalDateTime regdate;
}
