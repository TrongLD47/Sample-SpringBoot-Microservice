package com.dailycodebuffer.customer.domain.responsitory.jpa;

import com.dailycodebuffer.customer.domain.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<CustomerEntity, Long> {
}
