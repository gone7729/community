package project.community.user.web;

import lombok.Data;

@Data
public class SendAddress {
    private String email;
    private int regdate;
    private String key;
}
