package com.wynk.restaurant.restaurantservice.model;

import com.wynk.restaurant.restaurantservice.enums.OrderStatus;
import com.wynk.restaurant.restaurantservice.pojo.ItemAndQuantity;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class Order {
    private Long id;
    private Long userId;
    private Date orderTime;
    private Integer estimateTimeInMinutes;
    private Date deliveryTime;
    private List<ItemAndQuantity> orderList;
    private OrderStatus status;
    private Double payableAmount;
    private Double deliveryCharges;
    private Long partnerId;

    public Order(Long id, Long userId, Integer estimateTimeInMinutes, List<ItemAndQuantity> orderList, OrderStatus status, Double deliveryCharges) {
        this.id = id;
        this.userId = userId;
        this.estimateTimeInMinutes = estimateTimeInMinutes;
        this.orderList = orderList;
        this.status = status;
        this.deliveryCharges = deliveryCharges;
        this.orderTime = new Date();
        this.deliveryTime = new Date(orderTime.getTime() + estimateTimeInMinutes*60*1000);

    }
}
