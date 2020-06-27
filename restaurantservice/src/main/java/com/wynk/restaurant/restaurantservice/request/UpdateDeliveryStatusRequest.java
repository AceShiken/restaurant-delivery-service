package com.wynk.restaurant.restaurantservice.request;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDeliveryStatusRequest {
    private Long orderId;
}
