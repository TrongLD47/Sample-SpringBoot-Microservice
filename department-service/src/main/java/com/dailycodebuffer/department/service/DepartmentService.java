package com.dailycodebuffer.department.service;

import com.dailycodebuffer.department.dto.DepartmentDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDto);
    DepartmentDTO findDepartmentById(Long departmentId);
    DepartmentDTO getDepartmentByCode(String code);
    String ExportData(List<DepartmentDTO> dtoList, HttpServletRequest request) throws IOException;
    String exportExcel() throws IOException;
}
