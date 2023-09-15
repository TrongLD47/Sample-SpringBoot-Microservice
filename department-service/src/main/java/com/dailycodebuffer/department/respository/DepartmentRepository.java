package com.dailycodebuffer.department.respository;

import com.dailycodebuffer.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByDepartmentId(Long departmentId);
    DepartmentEntity findByDepartmentCode(String departmentCode);
}
