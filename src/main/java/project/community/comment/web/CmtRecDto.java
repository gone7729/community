package project.community.comment.web;

import lombok.Data;

@Data
public class CmtRecDto {
    private int uid;
    private String email;
    private int cmt_uid;
    private char rec_nrec;
}
