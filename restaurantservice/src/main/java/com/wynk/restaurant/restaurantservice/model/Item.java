package com.wynk.restaurant.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private Long id;
    private String name;
    private Double cost;
    private String description;

    public Item(Long id, String name, Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}
