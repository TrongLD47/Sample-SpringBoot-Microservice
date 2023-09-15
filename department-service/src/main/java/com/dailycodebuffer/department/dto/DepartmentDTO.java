package com.dailycodebuffer.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
    private String departmentDescription;
}
