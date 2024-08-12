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

import com.eduardocaio.inventory_control_project.dto.ProductDTO;
import com.eduardocaio.inventory_control_project.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Optional> create(@RequestBody ProductDTO product){
        productService.create(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product, @PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.update(product, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Optional> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
