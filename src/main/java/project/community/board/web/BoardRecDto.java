package project.community.board.web;

import lombok.Data;

@Data
public class BoardRecDto {
    private int uid;
    private String email;
    private int board_uid;
    private char rec_nrec;
}
