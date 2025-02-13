package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        assertEquals(10, new Calculator().add(5, 5), "5 + 5 should equal 10");
        assertEquals(4, new Calculator().add(2, 2), "2 + 2 should equal 4");
    }

    @Test
    public void testSub() {
        // assertEquals(10, new Calculator().add(5, 5), "5 + 5 should equal 10");
        assertEquals(0, new Calculator().sub(2, 2), "2 - 2 should equal 0");
    }
}

