package com.wynk.delivery.deliveryservice.dao;

import com.wynk.delivery.deliveryservice.dto.PartnerStatusResponse;
import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import com.wynk.delivery.deliveryservice.feign.FeignService;
import com.wynk.delivery.deliveryservice.model.Partner;
import com.wynk.delivery.deliveryservice.timer.DeliveryTimer;
import com.wynk.restaurant.restaurantservice.generator.IDGenerator;
import com.wynk.restaurant.restaurantservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Repository
public class PartnerDAO {

    private static List<Partner> allPartners;

    private static Set<Long> ordersSet = new HashSet<>();


    @Autowired
    FeignService orderService;

    static {
        allPartners = new ArrayList<>();
        allPartners.add(new Partner(IDGenerator.generateId(), null, "PartnerOne", 1234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(), null, "PartnerTwo", 2234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(), null, "PartnerThree", 3234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(), null, "PartnerFour", 4234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(),null, "PartnerFive", 5234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(),null, "PartnerSix", 6234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(),null, "PartnerSeven", 7234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(),null, "PartnerEight", 8234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(),null, "PartnerNine", 9234567899L, PartnerStatus.IDLE));
        allPartners.add(new Partner(IDGenerator.generateId(),null, "PartnerTen", 9934567899L, PartnerStatus.IDLE));
    }

    private List<Partner> getAllPartners() {
        return allPartners;
    }

    public List<Partner> getAvailablePartners() {
        return getAllPartners().stream().filter(partner -> PartnerStatus.IDLE.equals(partner.getStatus()))
                .collect(Collectors.toList());
    }

    public synchronized boolean assignOrderToPartner(Long orderId, Long partnerId) {
        List<Partner> partnerList = allPartners.stream().filter(partner -> partner.getId().equals(partnerId))
                .collect(Collectors.toList());
        if (!partnerList.isEmpty() && !ordersSet.contains(orderId)) {
            ordersSet.add(orderId);
            partnerList.get(0).setStatus(PartnerStatus.PICKED);
            partnerList.get(0).setOrderId(orderId);
            Thread deliveryTimerThread = new Thread(new DeliveryTimer(partnerList.get(0), orderService));
            deliveryTimerThread.start();
            return true;
        }
        return false;
    }

    public PartnerStatusResponse getPartnerStatus(Long partnerId) {
        // Check if it is a valid partnerId
        // If yes then proceed below->
        // Get order details from other service.
        // Subtract the delivery time from current time.
        //this.deliveryTime = new Date(orderTime.getTime() + estimateTimeInMinutes*60*1000);
        Partner partner = allPartners.stream().filter(partner1 -> partner1.getId().equals(partnerId))
                .findFirst().orElse(null);
        PartnerStatusResponse partnerStatusResponse = null;
        Long deliveryTimeLeft = 0L;
        if (partner != null && Optional.ofNullable(partner.getOrderId()).isPresent()) {
            Date current = new Date();
            deliveryTimeLeft = orderService.getOrderById(partner.getOrderId()).getDeliveryTime().getTime() - current.getTime();
        }
        return partnerStatusResponse = new PartnerStatusResponse(partner.getStatus(), deliveryTimeLeft);
    }

}
