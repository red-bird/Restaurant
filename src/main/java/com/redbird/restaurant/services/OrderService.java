package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();
    public Order save(Order order);
    public void delete(Long id);
}
