package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Food;

import java.util.List;

public interface FoodService {
    public List<Food> findAll();
    public Food save(Food food);
    public void delete(Long id);
}
