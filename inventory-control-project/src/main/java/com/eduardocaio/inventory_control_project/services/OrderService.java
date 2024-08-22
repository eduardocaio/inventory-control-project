package com.eduardocaio.inventory_control_project.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.inventory_control_project.dto.OrderDTO;
import com.eduardocaio.inventory_control_project.dto.OrderItemDTO;
import com.eduardocaio.inventory_control_project.entities.OrderEntity;
import com.eduardocaio.inventory_control_project.entities.OrderItemEntity;
import com.eduardocaio.inventory_control_project.entities.enums.OrderStatus;
import com.eduardocaio.inventory_control_project.entities.pk.AddressPK;
import com.eduardocaio.inventory_control_project.repositories.OrderRepository;
import com.google.gson.Gson;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InventoryService inventoryService;

    public List<OrderDTO> findAll() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(OrderDTO::new).toList();
    }

    public OrderDTO create(OrderDTO order) throws Exception {

        OrderEntity orderEntity = new OrderEntity(order);

        for (OrderItemDTO orderItemDTO : order.getOrderItems()) {
            orderEntity.addOrderItem(new OrderItemEntity(orderItemDTO, orderEntity));
            inventoryService.removeItems(orderItemDTO.getItem().getId(), orderItemDTO.getQuantity());
        }

        SearchCep(order.getCep(), orderEntity);

        orderEntity.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        return new OrderDTO(orderRepository.save(orderEntity));

    }

    private void SearchCep(String cepOrder, OrderEntity orderEntity) throws Exception {
        URL url = new URL("https://viacep.com.br/ws/" + cepOrder + "/json/");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String cep = "";
        StringBuilder jsonCep = new StringBuilder();

        while ((cep = br.readLine()) != null) {
            jsonCep.append(cep);
        }

        AddressPK aux = new Gson().fromJson(jsonCep.toString(), AddressPK.class);
        orderEntity.setCep(aux.getCep());
        orderEntity.setStreet(aux.getLogradouro());
        orderEntity.setComplement(aux.getComplemento());
        orderEntity.setZone(aux.getBairro());
        orderEntity.setCity(aux.getLocalidade());
        orderEntity.setUf(aux.getUf());
    }
}
