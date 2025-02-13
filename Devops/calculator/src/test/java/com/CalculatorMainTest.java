package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorMainTest {
    private Calculator calculator;

    @BeforeAll
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(30, calculator.add(10, 20));
    }

    @Test
    public void testSubtract() {
        assertEquals(10, calculator.subtract(20, 10));
    }

    @Test
    public void testMultiply() {
        assertEquals(200, calculator.multiply(20, 10));
    }
}
