package project.community.board.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class Board {
    private int uid;
    private int categoryNo;
    private String category;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private int view;
    private int upPoint;
    private int downPoint;
    private int reporting;
}
