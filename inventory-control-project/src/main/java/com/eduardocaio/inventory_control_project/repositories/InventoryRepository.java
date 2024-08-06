package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.inventory_control_project.entities.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long>{

}
