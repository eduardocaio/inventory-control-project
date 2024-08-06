package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.InventoryDTO;
import com.eduardocaio.inventory_control_project.entities.InventoryEntity;
import com.eduardocaio.inventory_control_project.repositories.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<InventoryDTO> findAll(){
        List<InventoryEntity> inventory = inventoryRepository.findAll();
        return inventory.stream().map(InventoryDTO::new).toList();
    }

}
