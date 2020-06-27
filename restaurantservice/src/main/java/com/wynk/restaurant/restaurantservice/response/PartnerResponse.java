package com.wynk.restaurant.restaurantservice.response;


import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import com.wynk.restaurant.restaurantservice.pojo.SimplePartner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartnerResponse {
    List<SimplePartner> partners;
}
