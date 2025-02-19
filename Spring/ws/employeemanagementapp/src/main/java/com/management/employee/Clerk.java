package com.management.employee;

final class Clerk extends Employee {

    private Clerk(String name, int id, int age) {
        super(name, id, age, EmployeeType.CLERK);
        this.salary = 10000;
    }

    protected static Clerk getEmployee(String name, int id, int age) {
        return new Clerk(name, id, age);
    }

    void raiseSalary() {
        this.salary += 2000;
    }
}

