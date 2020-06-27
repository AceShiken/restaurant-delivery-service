package com.wynk.restaurant.restaurantservice.service;

import com.wynk.restaurant.restaurantservice.dao.ItemsDAO;
import com.wynk.restaurant.restaurantservice.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemsDAO itemsDAO;

    public List<Item> getMenu() {
        return itemsDAO.getAllItems();
    }
}
