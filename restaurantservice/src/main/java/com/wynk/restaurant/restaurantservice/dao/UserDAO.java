package com.wynk.restaurant.restaurantservice.dao;

import com.wynk.restaurant.restaurantservice.generator.IDGenerator;
import com.wynk.restaurant.restaurantservice.model.User;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    private static List<User> allUsers;

    static {
        allUsers = new ArrayList<>();
        allUsers.add(new User(IDGenerator.generateId(), "admin", 9876543210l));
    }

    private List<User> getAllUsers() {
        return allUsers;
    }

    public User getUser() {
        return getAllUsers().get(0);
    }
}
