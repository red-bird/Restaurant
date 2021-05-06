package com.redbird.restaurant.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String description;
    private Double price;
    @ManyToMany(mappedBy = "foods", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
