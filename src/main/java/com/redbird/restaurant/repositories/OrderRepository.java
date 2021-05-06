package com.redbird.restaurant.repositories;

import com.redbird.restaurant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
