package com.wynk.restaurant.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private List<Long> orderIds;
    private String name;
    private Long contact;
    public User(Long id, String name, Long contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;

    }
}
