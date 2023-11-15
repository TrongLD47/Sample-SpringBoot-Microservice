package dailycodebuffer.email.service.Impl;

import dailycodebuffer.email.Entity.EmailDetails;
import dailycodebuffer.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}") private String sender;

    public void sendMail(String to, String subject, String body, boolean isHTML) throws Exception {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            mimeMessage.setFrom(sender);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            // Nội dung HTML
            if (isHTML) {
                // Thiết lập nội dung của email
                mimeMessage.setContent(body, "text/html");
            }
            // Gửi email
            emailSender.send(mimeMessage);

            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMail(EmailDetails details) {
        try {
            Context context = new Context();
            Map<String, Object> properties = details.getProperties();
            if (!properties.isEmpty()) {
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    context.setVariable(entry.getKey(), entry.getValue());
                }
            }
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");


            mimeMessageHelper.setSubject(details.getSubject());
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setFrom(sender);
            if (Objects.nonNull(details.getAttachment())) {
                // Adding the attachment
                FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
                mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
            String htmlContent = templateEngine.process("submit_order.html", context);
            mimeMessageHelper.setText(htmlContent, true);

            // Send email
            emailSender.send(mimeMessage);
            log.info("Sending email success to: " + details.getRecipient());
        } catch (MessagingException e) {
            log.error("Error when preparing or sending email to: " + details.getRecipient(), e);
        } catch (Exception e) {
            log.error("An unexpected error occurred when sending email to: " + details.getRecipient(), e);
        }
    }
}
