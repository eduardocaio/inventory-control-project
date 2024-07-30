package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.inventory_control_project.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
