package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.UserDTO;
import com.eduardocaio.inventory_control_project.entities.UserEntity;
import com.eduardocaio.inventory_control_project.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<UserDTO> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public void create(UserDTO user){
        UserEntity userEntity = new UserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    

    public UserDTO update(UserDTO user, Long id){
        UserEntity userEntity = new UserEntity(user);
        userEntity.setId(id);
        return new UserDTO(userRepository.save(userEntity));
    }

    public void delete(Long id){
        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public UserEntity findByEmail(String email){
        UserEntity user = userRepository.findByEmail(email).get();
        return user;
    }

}
