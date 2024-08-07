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
    
    public void create(CategoryDTO category){
        categoryRepository.save(new CategoryEntity(category));
    }

    public CategoryDTO update(CategoryDTO category, Long id){
        category.setId(id);
        CategoryEntity categoryEntity = new CategoryEntity(category);
        categoryRepository.save(categoryEntity);
        return new CategoryDTO(categoryEntity);
    }

    public void delete(Long id){
        CategoryEntity category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
    }

}
