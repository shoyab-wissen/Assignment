package com;

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}

public class CalculatorMain {
    public static void main(String[] args) {
        System.out.println("---------Calculator---------");
        Calculator calculator = new Calculator();
        System.out.println("Addition: " + calculator.add(10, 20));
        System.out.println("Subtraction: " + calculator.subtract(20, 10));

    }
}
