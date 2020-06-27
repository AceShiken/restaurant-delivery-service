package com.wynk.restaurant.restaurantservice.generator;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class IDGeneratorTest {

    @Test
    public void testGenerateId() {
        long generatedId = IDGenerator.generateId();
        Assert.assertNotEquals(generatedId, 1l/2);
        Assert.assertNotEquals(generatedId, 10l*2);
    }
}
