package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.inventory_control_project.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}
