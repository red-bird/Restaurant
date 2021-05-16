package com.redbird.restaurant.models.dto;

import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.models.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends Order {
    List<Good> goods;
}
