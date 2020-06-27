package com.wynk.restaurant.restaurantservice.feign;

import com.wynk.delivery.deliveryservice.dto.PartnerStatusResponse;
import com.wynk.delivery.deliveryservice.model.Partner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "delivery-service", url = "localhost:8100/delivery/")
public interface FeignService {
    @GetMapping("/getAllAvailablePartner")
    public List<Partner> getAllAvailablePartner();

    @PostMapping(value = "/assignOrder")
    public ResponseEntity<String> assignOrder(@RequestParam Long orderId, @RequestParam Long partnerId);

    @GetMapping("/partnerStatus/{partnerId}")
    public PartnerStatusResponse getPartnerStatus(@PathVariable("partnerId") Long partnerId);
}
