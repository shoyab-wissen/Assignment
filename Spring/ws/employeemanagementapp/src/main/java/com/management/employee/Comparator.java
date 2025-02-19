package com.management.employee;

import java.util.Comparator;

class NameSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.name.compareTo(o2.name);
    }

}

class DesignationSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.designation.toString().compareTo(o2.designation.toString());
    }

}

class IDSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.valueOf(o1.id).compareTo(o2.id);
    }
}

class AgeSort implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.valueOf(o1.age).compareTo(o2.age);
    }
}

class SalarySort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.valueOf(o1.salary).compareTo(o2.salary);
    }

}
