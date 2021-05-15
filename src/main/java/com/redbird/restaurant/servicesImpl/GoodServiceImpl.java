package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Good;
import com.redbird.restaurant.repositories.GoodRepository;
import com.redbird.restaurant.services.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodServiceImpl implements GoodService {

    private final GoodRepository goodRepository;

    public GoodServiceImpl(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Override
    public List<Good> findAllByClient(String client) {
        List<Good> allByClient = goodRepository.findAllByClient(client);
        log.info("Get all goods by username " + allByClient);
        return allByClient;
    }

    @Override
    public Good save(Good good) {
        Good save = goodRepository.save(good);
        log.info("Save good: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        goodRepository.deleteById(id);
        log.info("Delete good by id: " + id);
    }
}
