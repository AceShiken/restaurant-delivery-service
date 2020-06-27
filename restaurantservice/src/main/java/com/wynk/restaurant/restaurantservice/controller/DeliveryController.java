package com.wynk.restaurant.restaurantservice.controller;

import com.wynk.restaurant.restaurantservice.request.UpdateDeliveryStatusRequest;
import com.wynk.restaurant.restaurantservice.response.PartnerResponse;
import com.wynk.restaurant.restaurantservice.response.UpdateDeliveryStatusResponse;
import com.wynk.restaurant.restaurantservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping(value = "/updateDeliveryStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UpdateDeliveryStatusResponse updateDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest request) throws InterruptedException {
        return deliveryService.completeOrder(request);
    }

    @GetMapping(value = "/getPartners")
    public PartnerResponse getPartners() {
        return deliveryService.getPartnersFromOrders();
    }
}
