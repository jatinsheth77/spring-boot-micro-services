package com.microservice.department.service;

import com.microservice.department.entity.Department;
import com.microservice.department.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        logger.debug("DepartmentService.saveDepartment");
        return departmentRepository.save(department);
    }

    public Optional<Department> findDepartmentById(Long id) {
        logger.debug("DepartmentService.findDepartmentById");
        return departmentRepository.findById(id);
    }
}
