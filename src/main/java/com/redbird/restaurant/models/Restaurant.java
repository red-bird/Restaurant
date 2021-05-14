package com.redbird.restaurant.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Адрес не может быть пустым")
    @Size(max = 64, message = "Адрес слишком длинный (максимум 64 символа)")
    private String address;
    @NotBlank(message = "Номер не может быть пустым")
    @Size(max = 16, message = "Номер слишком длинный (максимум 16 символа)")
    private String phone;
    @NotBlank(message = "Часы работы не могут быть пустым")
    @Size(max = 64, message = "Часы работы слишком длинные (максимум 64 символа)")
    private String worktime;
    @Size(max = 512, message = "Описание слишком длинное (максимум 512 символа)")
    private String description;
}
