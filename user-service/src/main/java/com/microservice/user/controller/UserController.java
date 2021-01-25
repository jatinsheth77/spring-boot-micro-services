package com.microservice.user.controller;

import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;
import com.microservice.user.vo.Department;
import com.microservice.user.vo.ResponseTemplateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        logger.debug("UserController.saveUser");
/*
        http://locahost:9002/v1/users
        {
            "name":"Jatin Sheth",
                "email":"jatinsheth77@gmail.com",
                "departmentId":1
        }

*/
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            return new ResponseEntity(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity findUserWithDeptByUserId(@PathVariable("id") Long id) {
        logger.debug("UserController.findUserById");
        ResponseTemplateVO responseObject = new ResponseTemplateVO();
        Optional<User> user = userService.findUserById(id);


        if (user.isPresent()) {
            Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/v1/departments/" + user.get().getDepartmentId(), Department.class);
            responseObject.setUser(user.get());
            responseObject.setDepartment(department);
            return new ResponseEntity(responseObject, HttpStatus.FOUND);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }


}
