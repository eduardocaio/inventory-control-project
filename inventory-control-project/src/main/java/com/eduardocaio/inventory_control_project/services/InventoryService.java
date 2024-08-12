package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.InventoryDTO;
import com.eduardocaio.inventory_control_project.dto.ProductDTO;
import com.eduardocaio.inventory_control_project.entities.InventoryEntity;
import com.eduardocaio.inventory_control_project.entities.ProductEntity;
import com.eduardocaio.inventory_control_project.repositories.InventoryRepository;
import com.eduardocaio.inventory_control_project.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<InventoryDTO> findAll(){
        List<InventoryEntity> inventory = inventoryRepository.findAll();
        return inventory.stream().map(InventoryDTO::new).toList();
    }

    public InventoryDTO findById(Long id){
        return new InventoryDTO(inventoryRepository.findById(id).get());
    }

    public void insert(InventoryDTO inventory){
        ProductEntity product = productRepository.findById(inventory.getProduct().getId()).get();
        InventoryEntity inventoryEntity = new InventoryEntity(inventory);
        inventoryEntity.setProduct(product);
        inventoryRepository.save(inventoryEntity);
    }

    public InventoryDTO removeItems(Long id, int quantity){
        InventoryEntity item = inventoryRepository.findById(id).get();
        if(item.getQuantity() >= quantity){
            item.removeItems(quantity);
        }
        inventoryRepository.save(item);
        return new InventoryDTO(item);
    }

    

}
