package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    private final FoodService foodService;

    public HomeController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public String home(Map<String, Object> model) {
        model.put("foodList", foodService.findAll());
        return "home";
    }
}
