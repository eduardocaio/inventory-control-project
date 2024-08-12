package com.eduardocaio.inventory_control_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.InventoryDTO;
import com.eduardocaio.inventory_control_project.services.InventoryService;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> findAll(){
        return inventoryService.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<InventoryDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(inventoryService.findById(id));
    }

    @PostMapping
    public void insert(@RequestBody InventoryDTO inventory){
        inventoryService.insert(inventory);
    }

    @GetMapping(value = "/removeItems/{id}/{quantity}")
    public ResponseEntity<InventoryDTO> removeItems(@PathVariable("id") Long id, @PathVariable("quantity") int quantity){
        return ResponseEntity.ok().body(inventoryService.removeItems(id, quantity));
    }
}
