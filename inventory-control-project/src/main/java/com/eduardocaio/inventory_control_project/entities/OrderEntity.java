package com.eduardocaio.inventory_control_project.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItemEntity> orderItems = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    

    public OrderEntity(Long id, Date moment, UserEntity client, Set<OrderItemEntity> orderItems) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        this.orderItems = orderItems;
    }

    public OrderEntity(OrderDTO orderDTO) {
       this.id = orderDTO.getId();
       this.moment = orderDTO.getMoment();
       if(orderDTO != null && orderDTO.getClient() != null){
       this.client = new UserEntity(orderDTO.getClient());
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

    public void addOrderItem(OrderItemEntity orderItem){
        orderItems.add(orderItem);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
