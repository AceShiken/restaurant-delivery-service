package com.wynk.restaurant.restaurantservice.response;

import com.wynk.restaurant.restaurantservice.enums.OrderStatus;
import com.wynk.restaurant.restaurantservice.pojo.ItemAndQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private Date orderTime;
    private Integer estimateTimeInMinutes;
    private Date deliveryTime;
    private List<ItemAndQuantity> orderList;
    private OrderStatus status;
    private Double payableAmount;
}
