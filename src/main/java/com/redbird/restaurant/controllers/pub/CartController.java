package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.repositories.FoodRepository;
import com.redbird.restaurant.services.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('permission:user')")
@RequestMapping("/cart")
public class CartController {

    private final GoodService goodService;
    private final FoodRepository foodRepository;

    public CartController(GoodService goodService, FoodRepository foodRepository) {
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

    @PostMapping("save")
    public String saveGood(
            Principal principal,
            @RequestParam Food foodId
    ) {
        Good good = new Good();
        good.setClient(principal.getName());
//        good.setFood(foodId);
        good.setName(foodId.getName());
        good.setPrice(foodId.getPrice());
        good.setFilename(foodId.getFilename());
        good.setQuantity(1);
        goodService.save(good);
        return "redirect:/food";
    }

    @PostMapping()
    public String updateGood(
            @RequestParam Good goodId,
            @RequestParam Integer quantity,
            Principal principal,
            Model model
    ) {
        if (quantity < 1) {
            model.addAttribute("quantityError", "Должна быть хотя-бы одна единица");
            List<Good> goodList = goodService.findAllByClient(principal.getName());
            model.addAttribute("goodList", goodList);
            return "cart";
        }

        goodId.setQuantity(quantity);
        Good save = goodService.update(goodId);
        log.info("update Good: " + save);
        return "redirect:/cart";
    }

    @PostMapping("delete")
    public String deleteGood(@RequestParam Long id) {
        goodService.delete(id);
        return "redirect:/cart";
    }

    @PostMapping("order")
    public String makeOrder(Principal principal) {
        Order order = goodService.makeOrder(principal.getName());
        if (order == null) {
            log.error("Can't create order, 0 goods");
        }
        else {
            log.info("Order created: " + order);
        }
        return "redirect:/cart";
    }
}
