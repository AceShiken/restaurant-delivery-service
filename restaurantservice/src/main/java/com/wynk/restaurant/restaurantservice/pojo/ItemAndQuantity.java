package com.wynk.restaurant.restaurantservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemAndQuantity {
    private String name;
    private Integer quantity;
}
