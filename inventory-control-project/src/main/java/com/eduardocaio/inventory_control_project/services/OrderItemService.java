package com.eduardocaio.inventory_control_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.repositories.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

}
