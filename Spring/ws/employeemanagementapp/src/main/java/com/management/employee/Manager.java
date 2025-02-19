package com.management.employee;

public final class Manager extends Employee {

    private Manager(String name, int id, int age) {
        super(name, id, age, EmployeeType.MANAGER);
        this.salary = 100000;
    }

    static protected Manager getEmployee(String name, int id, int age) {
        return new Manager(name, id, age);
    }

    void raiseSalary() {
        this.salary += 10000;
    }

}
