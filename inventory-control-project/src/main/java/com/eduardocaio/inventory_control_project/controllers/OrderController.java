package com.eduardocaio.inventory_control_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDTO> findAll(){
        return orderService.findAll();
    }

}
