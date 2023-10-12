package project.community.user.web;

import lombok.Data;

import java.util.Date;

@Data
public class SendAddress {
    private String email;
    private Date regdate;
    private String key;
}
