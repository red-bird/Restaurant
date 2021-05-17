package com.redbird.restaurant.repositories;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAllByClient(String client);
    Good findFirstByClientAndName(String client, String name);
    List<Good> findAllByOrder(Order order);
}
