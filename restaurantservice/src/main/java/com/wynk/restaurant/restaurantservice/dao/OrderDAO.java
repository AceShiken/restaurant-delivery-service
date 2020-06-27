package com.wynk.restaurant.restaurantservice.dao;

import com.wynk.restaurant.restaurantservice.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAO {
    private static List<Order> orderList;

    static {
        orderList = new ArrayList<>();
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public Order getOrderById(Long id) {
        for(Order order : getAllOrders()) {
            if(order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }
}
