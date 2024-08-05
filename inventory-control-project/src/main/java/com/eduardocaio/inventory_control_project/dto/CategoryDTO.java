package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.CategoryEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;

    public CategoryDTO(CategoryEntity category){
        BeanUtils.copyProperties(category, this);
    }

}
