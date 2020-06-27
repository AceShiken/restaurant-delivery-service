package com.wynk.delivery.deliveryservice.dto;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PartnerStatusResponse {
    private PartnerStatus status;
    private Long deliveryTimeLeft = 0L;
}
