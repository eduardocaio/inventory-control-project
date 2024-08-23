package com.eduardocaio.inventory_control_project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.RoleDTO;
import com.eduardocaio.inventory_control_project.services.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll(){
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @PostMapping
    public ResponseEntity<Optional> create(@RequestBody RoleDTO role){
        roleService.create(role);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO role){
        return ResponseEntity.ok().body(roleService.update(role));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Optional> delete(@PathVariable("id") Long id){
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
