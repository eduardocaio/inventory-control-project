package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.inventory_control_project.entities.JwtEntity;
import com.eduardocaio.inventory_control_project.entities.UserEntity;

import java.util.Optional;


public interface JwtRepository extends JpaRepository<JwtEntity, String>{

    Optional<JwtEntity> findByUser(UserEntity user);

}
