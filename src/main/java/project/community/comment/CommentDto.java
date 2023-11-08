package project.community.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class CommentDto {
    private int uid;
    private String writer;
    private int board_uid;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private LocalDate regdate;
    private int up_point;
    private int down_point;
    private int reporting;
}
