package com.redbird.restaurant.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = "goods")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToOne
//    private Food food;
    private String name;
    private Double price;
    private Integer quantity;
    private String client;
    private String filename;
    @ManyToOne
    private Order order;
}
