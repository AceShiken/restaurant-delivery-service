package com.wynk.delivery.deliveryservice.feign;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import com.wynk.restaurant.restaurantservice.request.UpdateDeliveryStatusRequest;
import com.wynk.restaurant.restaurantservice.response.OrderResponse;
import com.wynk.restaurant.restaurantservice.response.PartnerResponse;
import com.wynk.restaurant.restaurantservice.response.UpdateDeliveryStatusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "order-service", url= "localhost:8200/restaurant/")
public interface FeignService {

    @PostMapping(value = "/updateDeliveryStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UpdateDeliveryStatusResponse updateDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest request);

    @GetMapping(value = "/getPartners")
    public PartnerResponse getPartners();

    @GetMapping(value = {"/order/{id}"}, consumes = {"application/json"})
    public OrderResponse getOrderById(@PathVariable("id") Long id);

}
