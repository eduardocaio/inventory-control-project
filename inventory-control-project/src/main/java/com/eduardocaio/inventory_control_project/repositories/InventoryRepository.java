package com.eduardocaio.inventory_control_project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eduardocaio.inventory_control_project.entities.InventoryEntity;

import jakarta.persistence.LockModeType;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM InventoryEntity i WHERE i.id = :id")
    Optional<InventoryEntity> findByIdWithLock(@Param("id")Long id);

}
