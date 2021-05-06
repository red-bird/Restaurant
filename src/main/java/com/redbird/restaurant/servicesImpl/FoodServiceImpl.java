package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.repositories.FoodRepository;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> findAll() {
        List<Food> all = foodRepository.findAll();
        log.info("findAll() output: " + all);
        return all;
    }

    @Override
    public Food save(Food food) {
        log.info("save() input: " + food);
        Food save = foodRepository.save(food);
        log.info("save() output: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        log.info("delete() input: " + id);
        foodRepository.deleteById(id);
        log.info("delete() " + id + " success");
    }
}
