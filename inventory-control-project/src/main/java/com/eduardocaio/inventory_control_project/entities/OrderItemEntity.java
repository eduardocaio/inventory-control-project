package com.eduardocaio.inventory_control_project.entities;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.dto.OrderItemDTO;

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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public OrderItemEntity(Long id, ProductEntity item, int quantity, OrderEntity order) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.order = order;
    }

    public OrderItemEntity(OrderItemDTO order){
        BeanUtils.copyProperties(order, this);
        if(order != null && order.getItem() != null){
            this.item = new ProductEntity(order.getItem());
        }
        if(order != null && order.getOrder() != null){
            this.order = new OrderEntity(order.getOrder());
        }
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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
