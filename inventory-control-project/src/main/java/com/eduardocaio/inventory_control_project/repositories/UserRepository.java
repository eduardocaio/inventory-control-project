package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.inventory_control_project.entities.UserEntity;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
