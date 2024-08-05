package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.ProductEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private CategoryDTO category;

    public ProductDTO(ProductEntity product){
        BeanUtils.copyProperties(product, this);
        if(product != null && product.getCategory() != null){
            this.category = new CategoryDTO(product.getCategory());
        }
    }

}
