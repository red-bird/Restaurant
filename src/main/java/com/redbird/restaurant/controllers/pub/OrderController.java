package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.dto.OrderDto;
import com.redbird.restaurant.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('permission:user')")
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrdersByClient(
            Principal principal,
            Model model
    ) {
        List<OrderDto> dtoList = orderService.findAllByClient(principal.getName());
        model.addAttribute("orderList", dtoList);
        log.info("getAllOrdersByClient()" + dtoList);
        return "orders";
    }
}
