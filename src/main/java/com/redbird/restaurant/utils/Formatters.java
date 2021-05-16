package com.redbird.restaurant.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
public class Formatters {
    @Bean
    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
    }
}
