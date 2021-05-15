package com.redbird.restaurant.repositories;

import com.redbird.restaurant.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAllByClient(String client);
}
