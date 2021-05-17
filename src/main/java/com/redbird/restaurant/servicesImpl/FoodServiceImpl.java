package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Food;
import com.redbird.restaurant.models.Order;
import com.redbird.restaurant.repositories.FoodRepository;
import com.redbird.restaurant.services.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;
    private String path = System.getProperty("user.dir").replace('\\', '/') + "/src/main/resources/";
    private String truePath;

    @Override
    public List<Food> findAll() {
        List<Food> all = foodRepository.findAll();
        log.info("findAll() output: " + all);
        return all;
    }

    @Override
    public Food save(Food food, MultipartFile file) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + '.' + file.getOriginalFilename();    

            try {
                file.transferTo(new File(path + uploadPath + "/img/" + resultFilename));
            }
            catch (IOException e) {
                log.error(e.getMessage());
            }

            food.setFilename(resultFilename);
        }
        log.info("save() input: " + food);
        Food save = foodRepository.save(food);
        log.info("save() output: " + save);
        return save;
    }

    @Override
    public void delete(Long id) {
        Optional<Food> food = findById(id);
        if (food.isPresent()) {
            truePath = path + uploadPath + "/img/";
            if (food.get().getFilename() != null) {
                File file = new File(truePath + food.get().getFilename());
                if (file.delete()) {
                    log.info("delete() img of id: " + id);
                }
                else {
                    log.info("can't delete im of id: " + id);
                }
            }
            foodRepository.deleteById(id);
            log.info("delete() food id: " + id);
        }
    }

    @Override
    public Optional<Food> findById(Long id) {
        Optional<Food> byId = foodRepository.findById(id);
        log.info("findById(): " + byId);
        return byId;
    }
}
