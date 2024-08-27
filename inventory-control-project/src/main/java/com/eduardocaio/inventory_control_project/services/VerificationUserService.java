package com.eduardocaio.inventory_control_project.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.entities.UserEntity;
import com.eduardocaio.inventory_control_project.entities.VerificationUserEntity;
import com.eduardocaio.inventory_control_project.exceptions.VerificationUserException;
import com.eduardocaio.inventory_control_project.repositories.VerificationUserRepository;

@Service
public class VerificationUserService {

    @Autowired
    VerificationUserRepository verificationRepository;

    public int generateCode(UserEntity user){
        Optional<VerificationUserEntity> existsVerification = verificationRepository.findByUser(user);

        if(existsVerification.isEmpty()){
            VerificationUserEntity verification = new VerificationUserEntity(user);
            verificationRepository.save(verification);
            return verification.getCode();
        }else{
            VerificationUserEntity verificationUser = existsVerification.get();
            if(verificationUser.getExpiration().isAfter(Instant.now())){
                return verificationUser.getCode();
            }
            else{
                verificationRepository.delete(verificationUser);
                VerificationUserEntity verification = new VerificationUserEntity(user);
                verificationRepository.save(verification);
                return verification.getCode();
            }
        }

    }

    public String verifyCode(int code, UserEntity user){
        VerificationUserEntity verificationUser = verificationRepository.findByUser(user).get();
        if(verificationUser != null && verificationUser.getCode() == code && verificationUser.getExpiration().isAfter(Instant.now())){
            verificationRepository.delete(verificationUser);
            return "Usuário verificado!";
        }else if(verificationUser.getCode() != code){
            throw new VerificationUserException("O código informado está incorreto!");
        }else{
            verificationRepository.delete(verificationUser);
            throw new VerificationUserException("O código informado não é mais válido. Favor solicitar o envio de um novo código!");
        }
    }

}
