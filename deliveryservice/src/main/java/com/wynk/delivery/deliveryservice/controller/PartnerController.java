package com.wynk.delivery.deliveryservice.controller;

import com.wynk.delivery.deliveryservice.dto.PartnerStatusResponse;
import com.wynk.delivery.deliveryservice.model.Partner;
import com.wynk.delivery.deliveryservice.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping("/getAllAvailablePartner")
    public List<Partner> getAllAvailablePartner() {
        return partnerService.getAllAvailablePartner();
    }

    @PostMapping(value = "/assignOrder")
    public ResponseEntity<String> assignOrder(@RequestParam Long orderId, @RequestParam Long partnerId) {
        // assign order to the partnerId
        boolean result = partnerService.assignOrderToPartner(orderId, partnerId);
        if (result) {
            return ResponseEntity.ok("ACCEPTED");
        } else {
            return ResponseEntity.status(505).body("NOT ACCEPTED");
        }
    }

    @GetMapping("/partnerStatus/{partnerId}")
    public PartnerStatusResponse getPartnerStatus(@PathVariable("partnerId") Long partnerId) {
        return partnerService.getPartnerStatus(partnerId);
    }
}
