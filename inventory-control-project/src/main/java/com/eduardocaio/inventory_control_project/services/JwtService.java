package com.eduardocaio.inventory_control_project.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.LoginRequest;
import com.eduardocaio.inventory_control_project.entities.JwtEntity;
import com.eduardocaio.inventory_control_project.entities.RoleEntity;
import com.eduardocaio.inventory_control_project.entities.UserEntity;
import com.eduardocaio.inventory_control_project.repositories.JwtRepository;

@Service
public class JwtService {

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtRepository jwtRepository;

    public String generateToken(LoginRequest loginRequest){

        UserEntity user = userService.findByEmail(loginRequest.username());

        if(jwtRepository.findByUser(user).isPresent()){
            return jwtRepository.findByUser(user).get().getToken();
        }

        if(user == null || !user.isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("User or password is invalid!");
        }

        Instant now = Instant.now();
        Long expiresIn = 600L;    

        String scopes = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("backend-eduardocaio").subject(user.getId().toString()).expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).claim("scope", scopes).build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();



        jwtRepository.save(new JwtEntity(jwtValue, now.plusSeconds(expiresIn), user));

        return jwtValue;

    }

    public String verifyExistingToken(LoginRequest loginRequest){
        UserEntity user = userService.findByEmail(loginRequest.username());
        
        Optional<JwtEntity> optionalToken = jwtRepository.findByUser(user);


        if(optionalToken.isPresent()){
            JwtEntity token = optionalToken.get();
            if(!token.getExpiresIn().isBefore(Instant.now())){
            return token.getToken();
            }
            else{
                jwtRepository.delete(token);
                return null;
            }
        }else{
            return null;
        }
    }

    public Long expiresIn(String token){
        JwtEntity tokenJwt = jwtRepository.findById(token).get();

        Duration duration = Duration.between(tokenJwt.getExpiresIn(), Instant.now());
        
        Long secondsDifference = duration.getSeconds();

        return secondsDifference;

    }

}
