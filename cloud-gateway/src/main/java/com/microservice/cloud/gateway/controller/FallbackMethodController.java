package com.microservice.cloud.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class FallbackMethodController {

    @GetMapping("/userServiceFallback")
    public ResponseEntity userServiceFallbackMethod() {
        return new ResponseEntity("user service is talking longer then expected.Please try again later.",HttpStatus.OK);
    }

    @GetMapping("/departmentServiceFallback")
    public ResponseEntity departmentServiceFallbackMethod() {
        return  new ResponseEntity("department service is talking longer then expected.Please try again later.",HttpStatus.OK);
    }
}

