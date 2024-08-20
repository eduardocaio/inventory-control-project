package com.eduardocaio.inventory_control_project.entities.pk;

import com.eduardocaio.inventory_control_project.entities.OrderEntity;
import com.eduardocaio.inventory_control_project.entities.ProductEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class OrderItemPK {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
