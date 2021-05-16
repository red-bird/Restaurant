package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.models.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAllByClient(String client);
    List<Order> findAll();
    Order save(Order order);
    void delete(Long id);
}
