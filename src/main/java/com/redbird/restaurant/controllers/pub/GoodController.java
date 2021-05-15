package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.repositories.FoodRepository;
import com.redbird.restaurant.services.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('permission:user')")
@RequestMapping("/cart")
public class GoodController {

    private final GoodService goodService;
    private final FoodRepository foodRepository;

    public GoodController(GoodService goodService, FoodRepository foodRepository) {
        this.goodService = goodService;
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public String getAllByClient(
            Principal principal,
            Model model
    ) {
        List<Good> goodList = goodService.findAllByClient(principal.getName());
        model.addAttribute("goodList", goodList);
        log.info("findAllByClient() output: " + goodList);
        return "cart";
    }

    @PostMapping
    public String saveGood(
            Principal principal,
            @RequestParam Food foodId
    ) {
        Good good = new Good();
        good.setClient(principal.getName());
        good.setFood(foodId);
        good.setQuantity(1);
        goodService.save(good);
        return "redirect:/food";
//        Optional<Food> food = foodRepository.findById(foodId);
//        if (food.isPresent()) {
//            Good good = new Good();
//            good.setClient(principal.getName());
//            good.setFood(food.get());
//            good.setQuantity(1);
//            goodService.save(good);
//            return "redirect:/food";
//        }
//        return "redirect:/food";
    }

    @PutMapping
    public String updateGood(
            @RequestParam Good goodId,
            @RequestParam Integer quantity
    ) {
        goodId.setQuantity(quantity);
        Good save = goodService.save(goodId);
        log.info("update Good: " + save);
        return "redirect:/cart";
    }



    @DeleteMapping
    public String deleteGood(@RequestParam Long id) {
        goodService.delete(id);
        return "redirect:/cart";
    }
}
