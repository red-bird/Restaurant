package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    public List<Restaurant> findAll();

    public Restaurant save(Restaurant restaurant);

    public void delete(Long id);
}
