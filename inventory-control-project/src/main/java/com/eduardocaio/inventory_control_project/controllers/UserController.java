package com.eduardocaio.inventory_control_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.UserDTO;
import com.eduardocaio.inventory_control_project.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user){
        userService.create(user);
        return ResponseEntity.ok().build();
    }

}
