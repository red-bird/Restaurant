package com.redbird.restaurant.controllers.admin;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/food/admin")
@PreAuthorize("hasAuthority('permission:admin')")
public class AdminFoodController {

    private final FoodService foodService;

    public AdminFoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public String findAllFood(Model model) {
        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        log.info("findAllFood() output: " + foodList);
        return "foodAdmin";
    }

    @PostMapping
    public String saveFood(@RequestParam String name,
                           @RequestParam String type,
                           @RequestParam String description,
                           @RequestParam Double price) {
        Food food = new Food();
        food.setName(name);
        food.setType(type);
        food.setDescription(description);
        food.setPrice(price);
        log.info("saveFood() input: " + food);
        food = foodService.save(food);
        log.info("saveFood() output: " + food);
        return "redirect:/food/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {
        log.info("deleteFood() input: " + id);
        deleteFood(id);
        log.info("deleteFood() " + id + "success");
        return "redirect:/food/admin";
    }
}
