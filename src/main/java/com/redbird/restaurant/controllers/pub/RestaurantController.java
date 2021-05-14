package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.Restaurant;
import com.redbird.restaurant.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String findAllRestaurants(Model model) {
        List<Restaurant> restaurantList = restaurantService.findAll();
        model.addAttribute("restaurantList", restaurantList);
        log.info("findAllRestaurants() output: " + restaurantList);
        return "restaurant";
    }

    @PreAuthorize("hasAuthority('permission:admin')")
    @PostMapping
    public String saveRestaurant(@Valid Restaurant restaurant,
                                 BindingResult bindingResult,
                                 Model model)
    {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("restaurant", restaurant);
        } else {
            log.info("saveRestaurant() input: " + restaurant);
            restaurant = restaurantService.save(restaurant);
            log.info("saveRestaurant() output: " + restaurant);
            model.addAttribute("restaurant", null);
            model.addAttribute("message", "Ресторан был добавлен");
        }

        List<Restaurant> restaurantList = restaurantService.findAll();
        model.addAttribute("restaurantList", restaurantList);
        return "restaurant";
    }

    @PreAuthorize("hasAuthority('permission:admin')")
    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        log.info("deleteRestaurant() input: " + id);
        restaurantService.delete(id);
        log.info("deleteRestaurant() " + id + "success");
        return "redirect:/restaurants";
    }
}
