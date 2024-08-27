package com.eduardocaio.inventory_control_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.RoleDTO;
import com.eduardocaio.inventory_control_project.dto.UserDTO;
import com.eduardocaio.inventory_control_project.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO user){
        userService.create(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user){
        return ResponseEntity.ok().body(userService.update(user));
    }

    @PutMapping(value = "/update-email")
    public ResponseEntity<Void> updateEmail(@RequestBody UserDTO user){
        userService.updateEmail(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/add-{idRole}/user-{idUser}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<RoleDTO>> addRole(@PathVariable("idRole") Long idRole, @PathVariable("idUser") Long idUser){
        return ResponseEntity.ok().body(userService.addRole(idRole, idUser));
    }

    @DeleteMapping(value = "/remove-{idRole}/user-{idUser}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<RoleDTO>> removeRole(@PathVariable("idRole") Long idRole, @PathVariable("idUser") Long idUser){
        return ResponseEntity.ok().body(userService.removeRole(idRole, idUser));
    }

    

}
