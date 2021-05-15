package com.redbird.restaurant.services;

import com.redbird.restaurant.models.Good;

import java.util.List;

public interface GoodService {
    List<Good> findAllByClient(String client);

    Good save(Good good);

    void delete(Long id);
}
