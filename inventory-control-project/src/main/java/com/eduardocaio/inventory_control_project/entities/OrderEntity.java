package com.eduardocaio.inventory_control_project.entities;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date moment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    public OrderEntity(Long id, Date moment, UserEntity client) {
        this.id = id;
        this.moment = moment;
        this.client = client;
    }

    public OrderEntity(OrderDTO order){
        BeanUtils.copyProperties(order, this);
        if(order != null && order.getClient() != null){
            this.client = new UserEntity(order.getClient());
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderEntity other = (OrderEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    


}
