package com.eduardocaio.inventory_control_project.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.OrderEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OrderDTO {

    private Long id;
    private Date moment;
    private UserDTO client;
    private Set<OrderItemDTO> orderItem = new HashSet<>();

    public OrderDTO(OrderEntity order){
        BeanUtils.copyProperties(order, this);
        if(order != null && order.getClient() != null){
            this.client = new UserDTO(order.getClient());
        }
        if(order != null && order.getOrderItems() != null){
            this.orderItem = order.getOrderItems().stream().map(OrderItemDTO::new).collect(Collectors.toSet());
        }
    }

}
