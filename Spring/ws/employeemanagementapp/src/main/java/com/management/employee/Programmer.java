package com.management.employee;

final class Programmer extends Employee {
    double salary;

    private Programmer(String name, int id, int age) {
        super(name, id, age, EmployeeType.PROGRAMMER);
        this.salary = 50000;
    }

    protected static Programmer getEmployee(String name, int id, int age) {
        return new Programmer(name, id, age);
    }

    void raiseSalary() {
        this.salary += 5000;
    }
}