package com.eduardocaio.inventory_control_project.entities;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.dto.OrderItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order_item")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity item;

    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public OrderItemEntity(Long id, ProductEntity item, int quantity, OrderEntity order) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.order = order;
    }

    public OrderItemEntity(OrderItemDTO orderItem){
        BeanUtils.copyProperties(orderItem, this);
        if(orderItem != null && orderItem.getItem() != null){
        this.item = new ProductEntity(orderItem.getItem());
        }
        if(orderItem != null && orderItem.getOrder() != null){
        this.order = new OrderEntity(orderItem.getOrder());
        } 
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
        if(!order.getOrderItems().contains(this)){
            order.addOrderItem(this);
        }
    }

    public Long getId() {
        return id;
    }

    public ProductEntity getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
