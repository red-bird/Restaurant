package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.models.Order;

import java.util.List;
import java.util.Optional;

public interface GoodService {
    List<Good> findAllByClient(String client);

    Optional<Good> save(Good good);

    Good update(Good good);

    void delete(Long id);

    Good findByClientAndFood(String client, Food foodId);

    Order makeOrder(String client);
}
