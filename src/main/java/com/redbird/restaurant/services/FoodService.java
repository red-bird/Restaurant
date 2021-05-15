package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Food;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    public List<Food> findAll();
    public Food save(Food food, MultipartFile file);
    public void delete(Long id);

    Optional<Food> findById(Long id);
}
