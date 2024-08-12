package com.eduardocaio.inventory_control_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.eduardocaio.inventory_control_project.entities.InventoryEntity;

import jakarta.transaction.Transactional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long>{

    @Modifying
    @Transactional
    @Query("UPDATE InventoryEntity SET quantity = quantity - :amount WHERE product = :id")
    void removeItems(Long id, Integer amount);

}
