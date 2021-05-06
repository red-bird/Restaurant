package com.redbird.restaurant.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User client;

    private String date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "orders_foods",
            joinColumns = {
                @JoinColumn(name = "order_id", referencedColumnName = "id",
                        nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "food_id", referencedColumnName = "id",
                        nullable = false, updatable = false)})
    private List<Food> foods;
}
