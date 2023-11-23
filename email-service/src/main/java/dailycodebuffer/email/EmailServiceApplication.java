package dailycodebuffer.email;

import dailycodebuffer.email.Entity.EmailDetails;
import dailycodebuffer.email.service.Impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EmailServiceApplication {

    @Autowired
    private EmailServiceImpl emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() throws Exception {
        Map<String, Object> properties = new HashMap<>();
//        emailService.sendEmail("trong.le-duc@banvien.com.vn", "This Is Subject01", "this is body");

        EmailDetails details = new EmailDetails();
        details.setRecipient("trong.le-duc@banvien.com.vn");
        details.setBody("this is body");
        details.setSubject("This Is Subject01");
        details.setAttachment("D:\\Trong.le-duc_Project\\Demo\\Sample-SpringBoot-Microservice\\Chat box.pdf");
//        emailService.sendMail(details);
    }
}
