package com.management.employee;

public final class CEO extends Employee {
    private static CEO obj = null;

    private CEO(String name, int id, int age) {
        super(name, id, age, EmployeeType.CEO);
        System.out.println("CEO Object created");
    }

    void raiseSalary() {
        System.out.println("Salary of CEO cannot be raised");
    }

    public static CEO getObject() {
        if (obj == null) {
            obj = new CEO("Shoyab", 60, 1000);
            obj.salary = 1000000;
        }
        return obj;
    }
}
