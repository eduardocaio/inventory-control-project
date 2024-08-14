package com.eduardocaio.inventory_control_project.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.dto.OrderItemDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date moment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    @OneToMany(mappedBy = "order")
    private Set<OrderItemEntity> orderItems = new HashSet<>();

    public OrderEntity(Long id, Date moment, UserEntity client, Set<OrderItemEntity> orderItems) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        this.orderItems = orderItems;
    }

    public OrderEntity(OrderDTO order) {
        BeanUtils.copyProperties(order, this);
        if (order != null && order.getClient() != null) {
            this.client = new UserEntity(order.getClient());
        }
        if(order != null && order.getOrderItem() != null){
            this.orderItems = order.getOrderItem().stream().map(OrderItemEntity::new).collect(Collectors.toSet());
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }

    public Set<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

}
