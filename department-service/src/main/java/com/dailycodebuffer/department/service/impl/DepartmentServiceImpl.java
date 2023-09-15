package com.dailycodebuffer.department.service.impl;

import com.dailycodebuffer.department.dto.DepartmentDTO;
import com.dailycodebuffer.department.entity.DepartmentEntity;
import com.dailycodebuffer.department.respository.DepartmentRepository;
import com.dailycodebuffer.department.service.DepartmentService;
import com.dailycodebuffer.department.service.mapper.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO department) {
        log.info("Inside saveDepartment of DepartmentService");
        DepartmentEntity entity = departmentMapper.toEntity(department);
        return departmentMapper.toDto(departmentRepository.save(entity));
    }
    @Override
    public DepartmentDTO findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById of DepartmentService");
        return departmentMapper.toDto(departmentRepository.findByDepartmentId(departmentId));
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String code) {
        DepartmentEntity department = departmentRepository.findByDepartmentCode(code);

        DepartmentDTO departmentDto = departmentMapper.toDto(department);

        return departmentDto;
    }
}
