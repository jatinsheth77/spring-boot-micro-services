package com.microservice.department.controller;

import com.microservice.department.entity.Department;
import com.microservice.department.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/departments", produces = "application/json")
    public ResponseEntity saveDepartment(@RequestBody Department departmentRequest) {
        logger.debug("DepartmentController.saveDepartment");

/*
        http://locahost:9001/v1/departments
        {
                "departmentName": "IT",
                "departmentDescription": "Information Technology",
                "departmentCode": "IT-006"
        }
*/

        Department department = departmentService.saveDepartment(departmentRequest);

        if (department != null) {
            return new ResponseEntity(department, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/departments/{id}", produces = "application/json")
    public ResponseEntity findDepartmentById(@PathVariable("id") Long id) {
        logger.debug("DepartmentController.findDepartmentById");
        Optional<Department> departmentById = departmentService.findDepartmentById(id);
        if (departmentById.isPresent()) {
            return new ResponseEntity(departmentById, HttpStatus.FOUND);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
