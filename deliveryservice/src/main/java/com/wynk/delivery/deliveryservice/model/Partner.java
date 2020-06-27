package com.wynk.delivery.deliveryservice.model;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Partner {
    private Long id;
    private Long orderId;
    private String name;
    private Long contact;
    private PartnerStatus status;
}
