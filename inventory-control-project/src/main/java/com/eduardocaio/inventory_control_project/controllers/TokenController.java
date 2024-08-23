package com.eduardocaio.inventory_control_project.controllers;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.LoginRequest;
import com.eduardocaio.inventory_control_project.dto.LoginResponse;
import com.eduardocaio.inventory_control_project.dto.UserSignupDTO;
import com.eduardocaio.inventory_control_project.entities.RoleEntity;
import com.eduardocaio.inventory_control_project.services.UserService;


@RestController
@RequestMapping(value = "/auth")
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        
        var user = userService.findByEmail(loginRequest.username());

        if(user == null || !user.isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("User or password is invalid!");
        }

        Instant now = Instant.now();
        Long expiresIn = 300L;    

        String scopes = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder().issuer("mybackend").subject(user.getId().toString()).expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).claim("scope", scopes).build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

        }

        @PostMapping("/signup")
        public ResponseEntity<Optional> signup(@RequestBody UserSignupDTO user){
            userService.signup(user);
            return ResponseEntity.ok().build();
        }
        
        
    }
    


