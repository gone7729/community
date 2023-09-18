package project.community.comment.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    private int uid;
    private String writer;
    private int board_uid;
    private String content;
    private LocalDate regdate;
    private int up_point;
    private int down_point;
    private int reporting;
}
