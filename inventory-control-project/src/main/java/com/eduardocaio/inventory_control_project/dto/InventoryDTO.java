package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.InventoryEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryDTO {

    public ProductDTO productId;
    public String productName;
    public int quantity;

    public InventoryDTO(InventoryEntity inventory) {
        BeanUtils.copyProperties(inventory, this);
        if (inventory != null && inventory.getProduct() != null) {
            this.productId = new ProductDTO(inventory.getProduct());

        }
    }

    public ProductDTO getProductId() {
        return productId;
    }

    public void setProductId(ProductDTO productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

}
