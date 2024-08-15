package com.eduardocaio.inventory_control_project.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.OrderEntity;
import com.eduardocaio.inventory_control_project.entities.OrderItemEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class OrderDTO {

    private Long id;
    private Date moment;
    private UserDTO client;
    private Set<OrderItemDTO> orderItems = new HashSet<>();

    public OrderDTO(OrderEntity orderEntity) {
        BeanUtils.copyProperties(orderEntity, this);
        if (orderEntity != null && orderEntity.getClient() != null) {
            this.client = new UserDTO(orderEntity.getClient());
        }
        if (orderEntity != null && orderEntity.getOrderItems() != null) {
            for(OrderItemEntity orderItem : orderEntity.getOrderItems()){
                this.addOrderItem(orderItem);
            }
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public void setClient(UserDTO client) {
        this.client = client;
    }

    public void addOrderItem(OrderItemEntity orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem, this);
        orderItems.add(orderItemDTO);
    }

}
