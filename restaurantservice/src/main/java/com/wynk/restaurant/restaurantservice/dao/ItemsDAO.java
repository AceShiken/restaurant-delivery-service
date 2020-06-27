package com.wynk.restaurant.restaurantservice.dao;

import com.wynk.restaurant.restaurantservice.generator.IDGenerator;
import com.wynk.restaurant.restaurantservice.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemsDAO {
    private static List<Item> itemList;

    static {
        itemList = new ArrayList<>();
        itemList.add(new Item(IDGenerator.generateId(), "Roti", 12.0));
        itemList.add(new Item(IDGenerator.generateId(), "Rice", 30.0));
        itemList.add(new Item(IDGenerator.generateId(), "Naan", 16.0));
        itemList.add(new Item(IDGenerator.generateId(), "Butter Naan", 20.0));
        itemList.add(new Item(IDGenerator.generateId(), "Dal Makhani", 40.0));
        itemList.add(new Item(IDGenerator.generateId(), "Matar Paneer", 70.0));
        itemList.add(new Item(IDGenerator.generateId(), "Rajma", 50.0));
        itemList.add(new Item(IDGenerator.generateId(), "Chhole", 60.0));
        itemList.add(new Item(IDGenerator.generateId(), "Raita", 10.0));
        itemList.add(new Item(IDGenerator.generateId(), "Lassi", 40.0));

    }

    public List<Item> getAllItems() {
        return itemList;
    }

    public Item getItemByName(String name) {
        for(Item item : getAllItems()) {
            if(item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
