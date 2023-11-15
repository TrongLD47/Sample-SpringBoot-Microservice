package dailycodebuffer.email.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String body;
    private String subject;
    private String attachment;
    private Map<String, Object> properties;
}
