package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.CategoryDTO;
import com.eduardocaio.inventory_control_project.dto.ProductDTO;
import com.eduardocaio.inventory_control_project.entities.CategoryEntity;
import com.eduardocaio.inventory_control_project.entities.ProductEntity;
import com.eduardocaio.inventory_control_project.repositories.CategoryRepository;
import com.eduardocaio.inventory_control_project.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<ProductDTO> findAll(){
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).toList();
    }

    public void create(ProductDTO product){
        Long id = product.getCategory().getId();
        CategoryDTO category = new CategoryDTO(categoryRepository.findById(id).get());
        product.setCategory(category);
        productRepository.save(new ProductEntity(product));
    }

    public ProductDTO update(ProductDTO product, Long id){
        product.setId(id);
        return new ProductDTO(productRepository.save(new ProductEntity(product)));
    }

    public void delete(Long id){
        ProductEntity product = productRepository.findById(id).get();
        productRepository.delete(product);
    }
}
