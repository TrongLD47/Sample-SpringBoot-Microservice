package com.dailycodebuffer.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
    private String departmentDescription;
    private Timestamp createDate;
}
