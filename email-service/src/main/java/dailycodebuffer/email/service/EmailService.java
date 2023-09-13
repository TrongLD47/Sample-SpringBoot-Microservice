package dailycodebuffer.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String text) throws Exception {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("bapupg10@gmail.com", false).toString());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
//        Transport.send(message, message.getAllRecipients());

            emailSender.send(message);
        } catch (Exception e) {
            System.out.println("send failed, exception: " + e);
        }
    }




//    public void sendmail() throws Exception {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("<Your email>", "<Your pw>");
//            }
//        });
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress("<Your email>@gmail.com", false));
//
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("<Your email>@gmail.com"));
//        msg.setSubject("Tutorials point email");
//        msg.setContent("Tutorials point email", "text/html");
//        msg.setSentDate(new Date());
//
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("Tutorials point email", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
////        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
//        Transport.send(msg);
//        System.out.println("SEND OO! OKKK+++++++++++++++++++");
//    }
}
