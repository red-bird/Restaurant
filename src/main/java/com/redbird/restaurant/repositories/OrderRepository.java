package com.redbird.restaurant.repositories;

import com.redbird.restaurant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByClient(String client);
}
