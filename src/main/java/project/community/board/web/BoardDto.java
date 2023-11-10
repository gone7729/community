package project.community.board.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BoardDto {
    private int uid;
    private int categoryNo;
    private String category;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private int view;
    private int rec;
    private int nrec;
    private int downPoint;
    private int offSet;

}
