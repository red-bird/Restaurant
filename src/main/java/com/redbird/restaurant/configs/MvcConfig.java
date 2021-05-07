package com.redbird.restaurant.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    private String truePath;

    @PostConstruct
    public void init() {
        String path = System.getProperty("user.dir")
                .replace('\\', '/') + "/src/main/resources/";
        truePath = '/' + path + uploadPath + '/';
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + truePath);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
