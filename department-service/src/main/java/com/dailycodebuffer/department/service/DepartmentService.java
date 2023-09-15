package com.dailycodebuffer.department.service;

import com.dailycodebuffer.department.dto.DepartmentDTO;
import com.dailycodebuffer.department.entity.DepartmentEntity;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDto);
    DepartmentDTO findDepartmentById(Long departmentId);
    DepartmentDTO getDepartmentByCode(String code);

}
