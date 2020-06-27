package com.wynk.restaurant.restaurantservice.response;


import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDeliveryStatusResponse {
    private PartnerStatus status;
}
