package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.inventory_control_project.entities.UserEntity;
import com.eduardocaio.inventory_control_project.entities.VerificationUserEntity;
import java.util.Optional;



public interface VerificationUserRepository extends JpaRepository<VerificationUserEntity, Long>{

    Optional<VerificationUserEntity> findByUser(UserEntity user);


}
