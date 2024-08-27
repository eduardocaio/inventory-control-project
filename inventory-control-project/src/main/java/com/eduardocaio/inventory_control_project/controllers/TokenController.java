package com.eduardocaio.inventory_control_project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.LoginRequest;
import com.eduardocaio.inventory_control_project.dto.LoginResponse;
import com.eduardocaio.inventory_control_project.dto.UserSignupDTO;
import com.eduardocaio.inventory_control_project.services.JwtService;
import com.eduardocaio.inventory_control_project.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class TokenController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        Long expiresIn = 300L;
        String token = jwtService.verifyExistingToken(loginRequest);

        if (token == null) {
            token = jwtService.generateToken(loginRequest);
        } else {
            expiresIn = jwtService.expiresIn(token);
        }

        return ResponseEntity.ok(new LoginResponse(token, expiresIn));

    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserSignupDTO user) {
        userService.signup(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/verify/{code}")
    public ResponseEntity<LoginResponse> verifyUser(@PathVariable("code") int code, @RequestBody LoginRequest loginRequest){
        String verification = userService.verifyUser(code, loginRequest);
        if(verification.equals("Usuário não verificado!") || verification.equals("Excedido tempo de verificação do código!")){
            return null;
        }
        String token = jwtService.generateToken(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token, 300L));
    }

}
