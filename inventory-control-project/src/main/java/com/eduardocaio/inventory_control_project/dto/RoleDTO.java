package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;

    public RoleDTO(RoleEntity role){
        BeanUtils.copyProperties(role, this);
    }

}
