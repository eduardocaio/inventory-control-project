package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.OrderItemEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderItemDTO {

    private ProductDTO item;
    private int quantity;

    @JsonIgnore
    private OrderDTO order;

    public OrderItemDTO(OrderItemEntity orderItem, OrderDTO orderDTO){
       BeanUtils.copyProperties(orderItem, this);
        if(orderItem != null && orderItem.getItem() != null){
        this.item = new ProductDTO(orderItem.getItem());
        }
        if(orderItem != null && orderItem.getOrder() != null){
            this.order = order;
        } 
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
        
    }


}
