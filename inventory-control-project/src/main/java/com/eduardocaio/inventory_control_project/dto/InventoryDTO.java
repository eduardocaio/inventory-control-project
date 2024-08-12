package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.InventoryEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryDTO {

    public ProductDTO product;
    public int quantity;

    public InventoryDTO(InventoryEntity inventory) {
        BeanUtils.copyProperties(inventory, this);
        if (inventory != null && inventory.getProduct() != null) {
            this.product = new ProductDTO(inventory.getProduct());

        }
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProductId(ProductDTO productId) {
        this.product = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

}
