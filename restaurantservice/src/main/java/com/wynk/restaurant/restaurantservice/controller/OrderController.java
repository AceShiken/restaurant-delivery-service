package com.wynk.restaurant.restaurantservice.controller;

import com.wynk.restaurant.restaurantservice.model.Item;
import com.wynk.restaurant.restaurantservice.request.PlaceOrderRequest;
import com.wynk.restaurant.restaurantservice.response.OrderResponse;
import com.wynk.restaurant.restaurantservice.response.PlaceOrderResponse;
import com.wynk.restaurant.restaurantservice.service.ItemService;
import com.wynk.restaurant.restaurantservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/placeOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceOrderResponse placeOrder(@RequestBody PlaceOrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping(value = "/order/{id}")
    public OrderResponse getOrderById(@PathVariable("id") Long id) {
        return  orderService.getOrderById(id);
    }

    @GetMapping(value = "/order/getMenu")
    public List<Item> getMenu() {
        return  itemService.getMenu();
    }
}
