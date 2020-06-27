package com.wynk.restaurant.restaurantservice.request;

import com.wynk.restaurant.restaurantservice.pojo.ItemAndQuantity;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    private List<ItemAndQuantity> orderList;
}
