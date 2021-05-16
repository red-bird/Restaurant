package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.repositories.GoodRepository;
import com.redbird.restaurant.services.GoodService;
import com.redbird.restaurant.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodServiceImpl implements GoodService {

    private final GoodRepository goodRepository;
    private final DateTimeFormatter formatter;
    private final OrderService orderService;

    public GoodServiceImpl(GoodRepository goodRepository, DateTimeFormatter formatter, OrderService orderService) {
        this.goodRepository = goodRepository;
        this.formatter = formatter;
        this.orderService = orderService;
    }

    @Override
    public List<Good> findAllByClient(String client) {
        List<Good> allByClient = goodRepository.findAllByClient(client);
        allByClient = allByClient
                .stream()
                .filter(good -> good.getOrder() == null)
                .collect(Collectors.toList());
        log.info("Get all goods by username " + allByClient);
        return allByClient;
    }

    @Override
    public Optional<Good> save(Good good) {
        Good res = findByClientAndFood(good.getClient(), good.getFood());
        if (res != null) {
            log.info(good.getClient() + " already has " + good.getFood().getName() + " good");
            return Optional.empty();
        }
        Good save = goodRepository.save(good);
        log.info("Save good: " + save);
        return Optional.of(save);
    }

    @Override
    public Good update(Good good) {
        Good save = goodRepository.save(good);
        log.info("Update good: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        goodRepository.deleteById(id);
        log.info("Delete good by id: " + id);
    }

    @Override
    public Good findByClientAndFood(String client, Food food) {
        Good good = goodRepository.findFirstByClientAndAndFood(client, food);
        if (good == null || good.getOrder() != null) {
            return null;
        }
        log.info("findByClientAndFood(): " + good);
        return good;
    }

    @Override
    public Order makeOrder(String client) {
        List<Good> goods = findAllByClient(client);
        Order order = new Order();
        order.setClient(client);
        order.setDate(formatter.format(ZonedDateTime.now()));
        Order save = orderService.save(order);
        if (save != null) {
            for (Good good : goods) {
                good.setOrder(save);
            }
        }
        log.info("Order created: " + order);
        return order;
    }
}
