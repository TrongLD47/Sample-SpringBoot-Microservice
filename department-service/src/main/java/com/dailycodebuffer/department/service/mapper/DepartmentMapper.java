package com.dailycodebuffer.department.service.mapper;

import com.dailycodebuffer.common.service.mapper.EntityMapper;
import com.dailycodebuffer.department.dto.DepartmentDTO;
import com.dailycodebuffer.department.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, DepartmentEntity> {
}
