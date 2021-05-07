package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public String findAllFood(Model model) {
        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        log.info("findAllFood() output: " + foodList);
        return "food";
    }
}
