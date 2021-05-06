package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.repositories.OrderRepository;
import com.redbird.restaurant.services.OrderService;
import com.redbird.restaurant.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final SimpleDateFormat simpleDateFormat;

    public OrderServiceImpl(UserService userService, OrderRepository orderRepository, SimpleDateFormat simpleDateFormat) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.simpleDateFormat = simpleDateFormat;
    }

    @Override
    public List<Order> findAll() {
        List<Order> all = orderRepository.findAll();
        log.info("findAll() output: " + all);
        return all;
    }

    @Override
    public Order save(Order order) {
        log.info("save() input: " + order);
        Order save = orderRepository.save(order);
        log.info("save() output: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        log.info("delete() input: " + id);
        orderRepository.deleteById(id);
        log.info("delete() " + id + " success");
    }
}
