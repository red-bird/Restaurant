package com.redbird.restaurant.controllers.admin;

import com.redbird.restaurant.controllers.pub.ControllerUtils;
import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
            log.info("saveFood() input: " + food);
            food = foodService.save(food);
            log.info("saveFood() output: " + food);
            model.addAttribute("food", null);
            model.addAttribute("message", "Блюдо было добавлено");
        }

        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        return "food";
    }



    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {
        log.info("deleteFood() input: " + id);
        deleteFood(id);
        log.info("deleteFood() " + id + "success");
        return "redirect:/food/admin";
    }
}
