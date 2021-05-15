package com.redbird.restaurant.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 63, message = "Название слишком длинное (максимум 63 символа)")
    private String name;
    @Size(max = 31, message = "Отношение к кухне слишком длинное (максимум 31 символ)")
    private String type;
    @Size(max = 1023, message = "Описание слишком длинное (максимум 1023 символа)")
    private String description;
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть выше 0")
    @NotNull(message = "Цена должна быть указана")
    private Double price;
    private String filename;
//    @ManyToMany(mappedBy = "foods", fetch = FetchType.LAZY)
//    private Set<Order> orders;
}
