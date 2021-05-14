package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Restaurant;
import com.redbird.restaurant.repositories.RestaurantRepository;
import com.redbird.restaurant.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> all = restaurantRepository.findAll();
        log.info("get all restaurants " + all);
        return all;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        log.info("save() input: " + restaurant);
        Restaurant save = restaurantRepository.save(restaurant);
        log.info("save() output: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        log.info("delete() input: " + id);
        restaurantRepository.deleteById(id);
        log.info("delete() " + id + " success");
    }
}
