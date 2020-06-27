package com.wynk.restaurant.restaurantservice.service;

import com.wynk.delivery.deliveryservice.dto.PartnerStatusResponse;
import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import com.wynk.delivery.deliveryservice.model.Partner;
import com.wynk.restaurant.restaurantservice.dao.OrderDAO;
import com.wynk.restaurant.restaurantservice.enums.OrderStatus;
import com.wynk.restaurant.restaurantservice.feign.FeignService;
import com.wynk.restaurant.restaurantservice.model.Order;
import com.wynk.restaurant.restaurantservice.pojo.SimplePartner;
import com.wynk.restaurant.restaurantservice.request.UpdateDeliveryStatusRequest;
import com.wynk.restaurant.restaurantservice.response.PartnerResponse;
import com.wynk.restaurant.restaurantservice.response.UpdateDeliveryStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private FeignService feignService;

    public UpdateDeliveryStatusResponse pickOrder(UpdateDeliveryStatusRequest request) throws InterruptedException {
        Order order = orderDAO.getOrderById(request.getOrderId());
        // get all idle partners
        // assign order to a partner
        // if SUCCESS change to PICKED
        // else if try another partner
        // else no idle partner
        // thread sleep for next 10 mins

        List<Partner> availablePartners = feignService.getAllAvailablePartner();
        boolean isPartnerAssigned = false;
        while (!isPartnerAssigned) {
            for (Partner partner : availablePartners) {
                if (feignService.assignOrder(order.getId(), partner.getId()).getStatusCode().equals(HttpStatus.OK)) {
                    isPartnerAssigned = true;
                    order.setPartnerId(partner.getId());
                    break;
                }
            }
            if (!isPartnerAssigned) Thread.sleep(10 * 60 * 1000l);
        }
        order.setStatus(OrderStatus.PICKED);

        return new UpdateDeliveryStatusResponse(PartnerStatus.PICKED);
    }

    public UpdateDeliveryStatusResponse completeOrder(UpdateDeliveryStatusRequest request) {
        Order order = orderDAO.getOrderById(request.getOrderId());
        order.setStatus(OrderStatus.DELIVERED);

        return new UpdateDeliveryStatusResponse(PartnerStatus.REACHED);
    }

    public PartnerResponse getPartnersFromOrders() {
        List<SimplePartner> simplePartnerList = new ArrayList<>();
        for(Order order : orderDAO.getAllOrders()) {
            Long partnerId = order.getPartnerId();
            if(Optional.ofNullable(partnerId).isPresent() && !order.getStatus().equals(OrderStatus.DELIVERED)) {
                PartnerStatusResponse response =  feignService.getPartnerStatus(partnerId);
                PartnerStatus status = response.getStatus();
                simplePartnerList.add(new SimplePartner(partnerId, order.getId(), status));
            }
        }
        return new PartnerResponse(simplePartnerList);
    }
}
