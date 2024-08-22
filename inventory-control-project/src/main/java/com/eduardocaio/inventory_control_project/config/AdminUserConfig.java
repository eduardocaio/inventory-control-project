package com.eduardocaio.inventory_control_project.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eduardocaio.inventory_control_project.entities.RoleEntity;
import com.eduardocaio.inventory_control_project.entities.UserEntity;
import com.eduardocaio.inventory_control_project.repositories.RoleRepository;
import com.eduardocaio.inventory_control_project.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
     
        roleRepository.save(new RoleEntity(null, "Admin"));

        RoleEntity role = roleRepository.findById(1L).get();

        UserEntity user = new UserEntity(null, "Caio", passwordEncoder.encode("teste123"), "caio@gmail.com", "CaioEdu");
        user.setRoles(Set.of(role));
        userRepository.save(user);


    }

}
