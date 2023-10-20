package com.dailycodebuffer.department.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCommand {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
    private String departmentDescription;
    private Timestamp createDate;
    private MultipartFile fileUpLoad;
}
