package com.redbird.restaurant.controllers.admin;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/food/admin")
@PreAuthorize("hasAuthority('permission:admin')")
public class AdminFoodController {

    private final FoodService foodService;

    public AdminFoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Value("${upload.path}")
    private String uploadPath;
    private String path = System.getProperty("user.dir").replace('\\', '/') + "/src/main/resources/";

    @GetMapping
    public String findAllFood(Model model) {
        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        log.info("findAllFood() output: " + foodList);
        return "food";
    }

    @PostMapping
    public String saveFood(@RequestParam String name,
                           @RequestParam String type,
                           @RequestParam String description,
                           @RequestParam Double price,
                           @RequestParam("file") MultipartFile file)
    {
        Food food = new Food();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + '.' + file.getOriginalFilename();

            try {
                file.transferTo(new File(path + uploadPath + "/img/" + resultFilename));
            }
            catch (IOException e) {
                log.error(e.getMessage());
            }

            food.setFilename(resultFilename);
        }
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
