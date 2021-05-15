package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("food")
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

    @PreAuthorize("hasAuthority('permission:admin')")
    @PostMapping
    public String saveFood(@Valid Food food,
                           BindingResult bindingResult,
                           Model model,
                           @RequestParam("file") MultipartFile file)
    {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("food", food);
        } else {
            log.info("saveFood() input: " + food);
            food = foodService.save(food, file);
            log.info("saveFood() output: " + food);
            model.addAttribute("food", null);
            model.addAttribute("message", "Блюдо было добавлено");
        }

        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        return "food";
    }


    @PreAuthorize("hasAuthority('permission:admin')")
    @PostMapping("/delete")
    public String deleteFood(@RequestParam Long id) {
        log.info("deleteFood() input: " + id);
        foodService.delete(id);
        log.info("deleteFood() " + id + "success");
        return "redirect:/food";
    }
}
