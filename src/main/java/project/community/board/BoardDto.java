package project.community.board;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BoardDto {
    private int uid;
    private String category;
    private int categoryNo;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private int view;
    private int upPoint;
    private int downPoint;
    private int reporting;
    private int offSet;

}
