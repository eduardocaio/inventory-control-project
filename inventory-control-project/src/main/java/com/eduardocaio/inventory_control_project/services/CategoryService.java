package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.CategoryDTO;
import com.eduardocaio.inventory_control_project.entities.CategoryEntity;
import com.eduardocaio.inventory_control_project.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll(){
       List<CategoryEntity> categories = categoryRepository.findAll();
       return categories.stream().map(CategoryDTO::new).toList();
    }

}
