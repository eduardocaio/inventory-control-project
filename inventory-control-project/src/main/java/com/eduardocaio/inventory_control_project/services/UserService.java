package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.LoginRequest;
import com.eduardocaio.inventory_control_project.dto.RoleDTO;
import com.eduardocaio.inventory_control_project.dto.UserDTO;
import com.eduardocaio.inventory_control_project.dto.UserSignupDTO;
import com.eduardocaio.inventory_control_project.entities.RoleEntity;
import com.eduardocaio.inventory_control_project.entities.UserEntity;
import com.eduardocaio.inventory_control_project.entities.enums.StatusUser;
import com.eduardocaio.inventory_control_project.exceptions.DataAlreadyRegisteredException;
import com.eduardocaio.inventory_control_project.exceptions.UserNotFoundException;
import com.eduardocaio.inventory_control_project.repositories.RoleRepository;
import com.eduardocaio.inventory_control_project.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    VerificationUserService verificationService;

    @Autowired
    EmailImplService emailService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public List<UserDTO> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public void create(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    public UserDTO update(UserDTO user) {
        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("Usuário " + user.getId() + ", não encontrado!"));
        updateData(userEntity, user);
        return new UserDTO(userRepository.save(userEntity));
    }

    public void delete(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário " + id + ", não encontrado!"));
        userRepository.delete(user);
    }

    public UserEntity findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).get();
        return user;
    }

    public void signup(UserSignupDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleEntity role = roleRepository.findByName("BASIC").get();
        UserEntity userEntity = new UserEntity(user);
        userEntity.setStatus(StatusUser.PENDENTE);
        userEntity.addRoles(role);
        UserEntity userEntityVerify = userRepository.save(userEntity);
        int code = verificationService.generateCode(userEntityVerify);
        emailService.sendMailMessage(userEntity.getEmail(), "Confirmar e-mail - Loja xxxx", "Olá, é uma prazer ter você conosco. Para confirmar seu usuário em nossa loja, favor confirmar seu cadastro com o código: " + code);

    }

    public List<RoleDTO> addRole(Long idRole, Long idUser) {
        RoleEntity role = roleRepository.findById(idRole).get();
        UserEntity user = userRepository.findById(idUser).get();
        user.addRoles(role);
        userRepository.save(user);
        return user.getRoles().stream().map(RoleDTO::new).toList();
    }

    public List<RoleDTO> removeRole(Long idRole, Long idUser) {
        RoleEntity role = roleRepository.findById(idRole).get();
        UserEntity user = userRepository.findById(idUser).get();
        user.removeRoles(role);
        userRepository.save(user);
        return user.getRoles().stream().map(RoleDTO::new).toList();
    }

    public String verifyUser(int code, LoginRequest login){
        UserEntity userEntity = userRepository.findByEmail(login.username()).get();
        String verification = verificationService.verifyCode(code, userEntity);
        if(verification.equals("Usuário verificado!")){
            userEntity.setStatus(StatusUser.ATIVO);
            return verification;
        }
        return verification;
    }

    private void updateData(UserEntity userEntity, UserDTO userDTO){
        if(userDTO.getName() != null){
            userEntity.setName(userDTO.getName());
        }
        if(userDTO.getUsername() != null){
            if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
                throw new DataAlreadyRegisteredException("O nome de usuário " + userDTO.getUsername() + ", já está em uso!");
            }
            userEntity.setUsername(userDTO.getUsername());
        }
    }

}
