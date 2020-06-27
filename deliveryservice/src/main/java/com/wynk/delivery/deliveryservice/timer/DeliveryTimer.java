package com.wynk.delivery.deliveryservice.timer;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import com.wynk.delivery.deliveryservice.feign.FeignService;
import com.wynk.delivery.deliveryservice.model.Partner;
import com.wynk.restaurant.restaurantservice.Constants;
import com.wynk.restaurant.restaurantservice.request.UpdateDeliveryStatusRequest;
import lombok.Data;

@Data
public class DeliveryTimer implements Runnable {
    private Partner partner;
    private FeignService feignService;

    public DeliveryTimer(Partner partner, FeignService feignService) {
        this.partner = partner;
        this.feignService = feignService;
    }

    @Override
    public void run() {
        Integer deliveryTime = Constants.DEFAULT_ESTIMATED_TIME;
        try {
            partner.setStatus(PartnerStatus.TRANSIT);
            System.out.println("Partner has started to the delivery address.");
            Thread.sleep(deliveryTime * 60 * 1000);
            partner.setStatus(PartnerStatus.REACHED);
            feignService.updateDeliveryStatus(new UpdateDeliveryStatusRequest(partner.getOrderId()));
            System.out.println("Partner has reached to the delivery address.");
            partner.setStatus(PartnerStatus.IDLE);
            partner.setOrderId(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
