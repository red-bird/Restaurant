package com.redbird.restaurant.repositories;

import com.redbird.restaurant.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
