package com.redbird.restaurant.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Formatters {
    @Bean
    public SimpleDateFormat formatter() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    }
}
