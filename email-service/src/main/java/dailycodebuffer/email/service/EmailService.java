package dailycodebuffer.email.service;

import dailycodebuffer.email.Entity.EmailDetails;

public interface EmailService {

    void sendMail(EmailDetails details);
    void sendMail(String to, String subject, String body, boolean isHTML) throws Exception;
}
