package project.community.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class MailManager {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String sendTo, String sub, String con) throws Exception {
//		HTML 태그 그대로 전송하는 방법
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(sender);
        //받는사람
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
        mimeMessage.setSubject(sub);
        mimeMessage.setText(con);
        javaMailSender.send(mimeMessage);
    }
}
