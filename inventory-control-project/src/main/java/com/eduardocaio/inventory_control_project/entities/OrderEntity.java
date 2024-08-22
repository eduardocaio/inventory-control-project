package com.eduardocaio.inventory_control_project.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.entities.enums.OrderStatus;
import com.eduardocaio.inventory_control_project.entities.pk.AddressPK;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
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
public class OrderEntity implements Serializable{
    private static final long serialVersionUID = 1L;

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

    @Embedded
    private AddressPK address = new AddressPK();

    public OrderEntity(Long id, Date moment, UserEntity client, Set<OrderItemEntity> orderItems) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        this.orderItems = orderItems;
    }

    public OrderEntity(OrderDTO orderDTO) {
        this.id = orderDTO.getId();
        this.moment = orderDTO.getMoment();
        if (orderDTO != null && orderDTO.getClient() != null) {
            this.client = new UserEntity(orderDTO.getClient());
        }
        this.address = new AddressPK();
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

    public void addOrderItem(OrderItemEntity orderItem) {
        orderItems.add(orderItem);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCep(String cep){
        address.setCep(cep);
    }

    public void setStreet(String street){
        address.setLogradouro(street);
    }

    public void setComplement(String complement){
        address.setComplemento(complement);
    }

    public void setCity(String city){
        address.setLocalidade(city);
    }

    public void setZone(String zone){
        address.setBairro(zone);
    }

    public void setUf(String uf){
        address.setUf(uf);
    }

    public String getCep(){
        return address.getCep();
    }

    public String getStreet(){
        return address.getLogradouro();
    }

    public String getComplement(){
        return address.getComplemento();
    }

    public String getCity(){
        return address.getLocalidade();
    }

    public String getZone(){
        return address.getBairro();
    }

    public String getUf(){
        return address.getUf();
    }

}
