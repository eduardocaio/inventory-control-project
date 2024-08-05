package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.ProductDTO;
import com.eduardocaio.inventory_control_project.entities.ProductEntity;
import com.eduardocaio.inventory_control_project.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> findAll(){
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).toList();
    }

}
