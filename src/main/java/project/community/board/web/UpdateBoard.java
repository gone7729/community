package project.community.board.web;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateBoard {
    private int uid;
    @NotNull(message = "카테고리를 선택해주세요.")
    private int categoryNo;
    @NotBlank
    private String writer;
    @NotBlank(message = "제목을 작성해주세요.")
    private String title;
    @NotBlank(message = "내용을 작성해주세요.")
    private String content;
}
