package com.eduardocaio.inventory_control_project.entities;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.dto.OrderItemDTO;
import com.eduardocaio.inventory_control_project.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order_item")
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private int quantity;

    public OrderItemEntity(ProductEntity item, int quantity, OrderEntity order) {
        id.setProduct(item);
        this.quantity = quantity;
        id.setOrder(order);
    }

    public OrderItemEntity(OrderItemDTO orderItem) {
        BeanUtils.copyProperties(orderItem, this);
        if (orderItem != null && orderItem.getItem() != null) {
            id.setProduct(new ProductEntity(orderItem.getItem()));
        }
        if (orderItem != null && orderItem.getOrder() != null) {
            id.setOrder(new OrderEntity(orderItem.getOrder()));
        }
    }

    public OrderItemEntity(OrderItemDTO orderItem, OrderEntity order) {
        BeanUtils.copyProperties(orderItem, this);
        this.id = new OrderItemPK();
        if (orderItem != null && orderItem.getItem() != null) {
            id.setProduct(new ProductEntity(orderItem.getItem()));
        }
        if (orderItem != null && order != null) {
            id.setOrder(order);
        }
    }

    public OrderEntity getOrder() {
        return id.getOrder();
    }

    public void setOrder(OrderEntity order) {
        id.setOrder(order);
        if (!order.getOrderItems().contains(this)) {
            order.addOrderItem(this);
        }
    }


    public ProductEntity getItem() {
        return id.getProduct();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
