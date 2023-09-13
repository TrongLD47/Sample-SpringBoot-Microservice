package dailycodebuffer.email.controller;

import dailycodebuffer.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/emails")
@Slf4j
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send")
    public String sendEmail(@RequestParam String sendTo, @RequestParam String subject, @RequestParam String content) {
        try {
//            emailService.sendmail();
            emailService.sendEmail(sendTo, subject, content);
            return "Email sent successfully!";
        } catch (Exception e) {
            log.error("Error sending email: " + e.getMessage());
            return "Error sending email: " + e.getMessage();
        }
    }
}
