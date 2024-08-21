package com.eduardocaio.inventory_control_project.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.entities.OrderEntity;
import com.eduardocaio.inventory_control_project.entities.OrderItemEntity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderDTO {

    private Long id;
    private Date moment;
    private UserDTO client;
    private Set<OrderItemDTO> orderItems = new HashSet<>();
    private String cep;

    public OrderDTO(OrderEntity orderEntity) {
        BeanUtils.copyProperties(orderEntity, this);
        if (orderEntity != null && orderEntity.getClient() != null) {
            this.client = new UserDTO(orderEntity.getClient());
        }
        for (OrderItemEntity orderItem : orderEntity.getOrderItems()) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem, this);
            addOrderItem(orderItemDTO);
        }

    }

    public Long getId() {
        return id;
    }

    public Date getMoment() {
        return moment;
    }

    public UserDTO getClient() {
        return client;
    }

    public Set<OrderItemDTO> getOrderItems() {
        return orderItems;
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

    public void addOrderItem(OrderItemDTO orderItem) {
        orderItems.add(orderItem);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
