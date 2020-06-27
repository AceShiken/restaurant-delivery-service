package com.wynk.restaurant.restaurantservice.generator;

public class IDGenerator {
    public static Long generateId() {
        long leftLimit = 100000L;
        long rightLimit = 999999L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }
}
