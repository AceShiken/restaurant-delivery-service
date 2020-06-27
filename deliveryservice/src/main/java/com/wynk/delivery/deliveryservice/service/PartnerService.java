package com.wynk.delivery.deliveryservice.service;

import com.wynk.delivery.deliveryservice.dao.PartnerDAO;
import com.wynk.delivery.deliveryservice.dto.PartnerStatusResponse;
import com.wynk.delivery.deliveryservice.model.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    @Autowired
    PartnerDAO partnerDAO;

    public List<Partner> getAllAvailablePartner() {
        return partnerDAO.getAvailablePartners();
    }

    public boolean assignOrderToPartner(Long orderId, Long personId) {
        return partnerDAO.assignOrderToPartner(orderId, personId);
    }

    public PartnerStatusResponse getPartnerStatus(Long partnerId) {
        return partnerDAO.getPartnerStatus(partnerId);
    }
}
