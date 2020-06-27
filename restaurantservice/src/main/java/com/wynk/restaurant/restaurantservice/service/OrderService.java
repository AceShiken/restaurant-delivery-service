package com.wynk.restaurant.restaurantservice.service;

import com.wynk.delivery.deliveryservice.enums.PartnerStatus;
import com.wynk.restaurant.restaurantservice.Constants;
import com.wynk.restaurant.restaurantservice.dao.ItemsDAO;
import com.wynk.restaurant.restaurantservice.dao.OrderDAO;
import com.wynk.restaurant.restaurantservice.dao.UserDAO;
import com.wynk.restaurant.restaurantservice.enums.OrderStatus;
import com.wynk.restaurant.restaurantservice.feign.FeignService;
import com.wynk.restaurant.restaurantservice.generator.IDGenerator;
import com.wynk.restaurant.restaurantservice.model.Item;
import com.wynk.restaurant.restaurantservice.model.Order;
import com.wynk.restaurant.restaurantservice.pojo.ItemAndQuantity;
import com.wynk.restaurant.restaurantservice.request.PlaceOrderRequest;
import com.wynk.restaurant.restaurantservice.request.UpdateDeliveryStatusRequest;
import com.wynk.restaurant.restaurantservice.response.OrderResponse;
import com.wynk.restaurant.restaurantservice.response.PlaceOrderResponse;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ItemsDAO itemsDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private DeliveryService deliveryService;


    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        Order order = new Order(IDGenerator.generateId(),
                userDAO.getUser().getId(),
                Constants.DEFAULT_ESTIMATED_TIME,
                request.getOrderList(),
                OrderStatus.ACCEPTED,
                Constants.DELIVERY_CHARGES);
        order.setPayableAmount(getPayableAmount(order));

        orderDAO.addOrder(order);

        return new PlaceOrderResponse(order.getId(), order.getStatus());

    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderDAO.getOrderById(id);
        return new OrderResponse(order.getOrderTime(),
                order.getEstimateTimeInMinutes(),
                order.getDeliveryTime(),
                order.getOrderList(),
                order.getStatus(),
                order.getPayableAmount());
    }

    private Double getPayableAmount(Order order) {
        Double amount = 0d;
        for(ItemAndQuantity orderAndQuantity : order.getOrderList()) {
            Item item = itemsDAO.getItemByName(orderAndQuantity.getName());
            amount += item.getCost()*orderAndQuantity.getQuantity();
        }
        return amount + order.getDeliveryCharges();
    }

    @Scheduled(fixedRate = 1* 60 * 1000l)
    private void updateOrderStatus() throws InterruptedException {
        for(Order order : orderDAO.getAllOrders()) {
            Date currentTime = new Date();
            if(OrderStatus.ACCEPTED.equals(order.getStatus()) && currentTime.getTime() - order.getOrderTime().getTime() >= 1*60*1000) {
                order.setStatus(OrderStatus.PREPARING);
            } else if(OrderStatus.PREPARING.equals(order.getStatus()) && currentTime.getTime() - order.getOrderTime().getTime() >= 2*60*1000) {
                order.setStatus(OrderStatus.READY);
                deliveryService.pickOrder(new UpdateDeliveryStatusRequest(order.getId()));
            }
//            } else if(OrderStatus.PICKED.equals(order.getStatus()) && currentTime.getTime() > order.getDeliveryTime().getTime()) {
//                order.setStatus(OrderStatus.DELIVERED);
//            }
        }
    }
}
