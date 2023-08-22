package project.community.board.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardDto {
    private int uid;
    private String writer;
    private String title;
    private String content;
    private LocalDate regdate;
    private int view;
    private int upPoint;
    private int downPoint;
    private int reporting;
}
