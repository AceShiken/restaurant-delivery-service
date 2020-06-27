package com.wynk.restaurant.restaurantservice.pojo;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimplePartner {
    private Long partnerId;
    private Long orderId;
    private PartnerStatus partnerStatus;
}
