package com.eduardocaio.inventory_control_project.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.RoleDTO;
import com.eduardocaio.inventory_control_project.entities.RoleEntity;
import com.eduardocaio.inventory_control_project.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> findAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        return roles.stream().map(RoleDTO::new).toList();
    }

    public void create(RoleDTO role){
        roleRepository.save(new RoleEntity(role));
    }

    public void delete(Long id){
        RoleEntity role = roleRepository.findById(id).get();
        roleRepository.delete(role);
    }

    public RoleDTO update(RoleDTO role){
        RoleEntity roleEntity = roleRepository.findById(role.getId()).get();
        roleEntity.setName(role.getName());
        return new RoleDTO(roleRepository.save(roleEntity));
    }

}
