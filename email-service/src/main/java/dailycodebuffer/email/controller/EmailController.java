package dailycodebuffer.email.controller;

import dailycodebuffer.email.Entity.EmailDetails;
import dailycodebuffer.email.service.Impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/emails")
@Slf4j
public class EmailController {
    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping(value = "/sendSimpleMail")
    public String sendSimpleEmail(@RequestParam String sendTo, @RequestParam String subject, @RequestParam String content) {
        try {
            emailService.sendMail(sendTo, subject, content, true);
            return "Email sent successfully!";
        } catch (Exception e) {
            log.error("Error sending email: " + e.getMessage());
            return "Error sending email: " + e.getMessage();
        }
    }

    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity sendMail(@RequestBody EmailDetails details) {
        try {
            emailService.sendMail(details);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("--- Email sent successfully ---");
    }
}
