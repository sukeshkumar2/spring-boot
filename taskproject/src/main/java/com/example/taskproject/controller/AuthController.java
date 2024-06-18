package com.example.taskproject.controller;

import com.example.taskproject.entity.Users;
import com.example.taskproject.payload.UserDTO;
import com.example.taskproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto)
    {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);

    }
}
