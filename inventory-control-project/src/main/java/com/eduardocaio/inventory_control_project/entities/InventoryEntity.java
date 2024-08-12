package com.eduardocaio.inventory_control_project.entities;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.dto.InventoryDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_inventory")
@NoArgsConstructor
public class InventoryEntity {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private String product_name;

    private int quantity;

    public InventoryEntity(ProductEntity product, int quantity) {
        this.product = product;
        this.product_name = product.getName();
        this.quantity = quantity;
    }

    public InventoryEntity(InventoryDTO inventory) {
        BeanUtils.copyProperties(inventory, this);
        if (inventory != null && inventory.getProduct() != null) {
            this.product = new ProductEntity(inventory.getProduct());
            this.product_name = product.getName();
        }
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(ProductEntity product) {
        this.product_name = product.getName();
    }

    public void removeItems(int quantity){
        this.quantity -= quantity;
    }

    public void addItems(int quantity){
        this.quantity += quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InventoryEntity other = (InventoryEntity) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }

    

}
