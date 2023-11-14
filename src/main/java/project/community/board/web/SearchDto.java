package project.community.board.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchDto {
    private int uid;
    private String ctg;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private String text;
    private int offSet;
}
