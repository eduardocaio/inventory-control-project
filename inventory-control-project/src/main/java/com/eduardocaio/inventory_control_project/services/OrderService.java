package com.eduardocaio.inventory_control_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.entities.OrderEntity;
import com.eduardocaio.inventory_control_project.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<OrderDTO> findAll(){
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(OrderDTO::new).toList();
    }

}
