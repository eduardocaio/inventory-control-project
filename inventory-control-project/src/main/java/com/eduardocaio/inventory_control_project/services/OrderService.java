package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.dto.OrderItemDTO;
import com.eduardocaio.inventory_control_project.entities.OrderEntity;
import com.eduardocaio.inventory_control_project.entities.OrderItemEntity;
import com.eduardocaio.inventory_control_project.entities.enums.OrderStatus;
import com.eduardocaio.inventory_control_project.repositories.OrderItemRepository;
import com.eduardocaio.inventory_control_project.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    InventoryService inventoryService;

    public List<OrderDTO> findAll() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(OrderDTO::new).toList();
    }

    public OrderDTO create(OrderDTO order) {

        OrderEntity orderEntity = new OrderEntity(order);

        for (OrderItemDTO orderItemDTO : order.getOrderItems()) {
            orderEntity.addOrderItem(new OrderItemEntity(orderItemDTO, orderEntity));
            inventoryService.removeItems(orderItemDTO.getItem().getId(), orderItemDTO.getQuantity());
        }

        orderEntity.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        return new OrderDTO(orderRepository.save(orderEntity));

    }
}
