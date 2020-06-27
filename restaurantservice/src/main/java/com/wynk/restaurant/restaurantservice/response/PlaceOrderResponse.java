package com.wynk.restaurant.restaurantservice.response;

import com.wynk.restaurant.restaurantservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResponse {
    private Long orderId;
    private OrderStatus orderStatus;
}
