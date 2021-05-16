package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.models.dto.OrderDto;
import com.redbird.restaurant.repositories.GoodRepository;
import com.redbird.restaurant.repositories.OrderRepository;
import com.redbird.restaurant.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final GoodRepository goodRepository;

    public OrderServiceImpl(OrderRepository orderRepository, GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> findAllByClient(String client) {
        List<Order> orders = orderRepository.findAllByClient(client);
        return getOrderDtos(orders);
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

    private List<OrderDto> getOrderDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new LinkedList<>();
        orders.forEach(order -> {
            List<Good> goods = goodRepository.findAllByOrder(order);
            orderDtos.add(makeOrderDto(order, goods));
        });
        return orderDtos;
    }

    private OrderDto makeOrderDto(Order order, List<Good> goods) {
        OrderDto orderDto = new OrderDto();
        orderDto.setDate(order.getDate());
        orderDto.setClient(order.getClient());
        orderDto.setGoods(goods);
        return orderDto;
    }
}
