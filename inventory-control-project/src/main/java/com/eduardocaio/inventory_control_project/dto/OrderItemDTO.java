package com.eduardocaio.inventory_control_project.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.OrderItemEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderItemDTO {

    private Long id;
    private ProductDTO item;
    private int quantity;
    private OrderDTO order;

    public OrderItemDTO(OrderItemEntity order){
        BeanUtils.copyProperties(order, this);
        if(order != null && order.getItem() != null){
            this.item = new ProductDTO(order.getItem());
        }
        if(order != null && order.getOrder() != null){
            this.order = new OrderDTO(order.getOrder());
        }
    }

}
