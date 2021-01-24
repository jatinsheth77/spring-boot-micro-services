package com.microservice.user.service;

import com.microservice.user.entity.User;
import com.microservice.user.reposistory.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        logger.debug("UserService.savedUser");
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Optional<User> findUserById(Long id) {
        logger.debug("UserService.getUserById");
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }
}
