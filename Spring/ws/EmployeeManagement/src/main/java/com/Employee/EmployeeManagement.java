package com.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;

public class EmployeeManagement {
    // static CEO ceo;
    // static {
    // ceo = CEO.getObject();
    // }
    static DatabaseHandler handler = DatabaseHandler.getHandler();
    static {
        EmployeeType.getDesignation(handler);
        EmployeeDepartment.getDepartments(handler);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();
        employees = handler.getEmployees(0, null);
        // employees.put(1, ceo);
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        try {

            while (true) {

                System.out.println("---------------------------");
                System.out.println("1. Create");
                System.out.println("2. Display");
                System.out.println("3. Raise Salary");
                System.out.println("4. Delete");
                System.out.println("5. Search Employee With ID");
                System.out.println("6. Exit");
                System.out.println("---------------------------");
                System.out.print("Enter your choice: ");
                int choice = Menu.readChoice(6, scanner);
                switch (choice) {
                    case 1 -> {
                        while (true) {
                            System.out.println("Select Employee Type");
                            System.out.println("---------------------------");
                            int i = 0;
                            for (i = 0; i < EmployeeType.employeeTypes.size(); i++) {
                                System.out.printf("%d. %s\n", i + 1, EmployeeType.employeeTypes.get(i));
                            }
                            System.out.printf("%d. Other\n", i + 1);
                            System.out.printf("%d. Exit\n", i + 2);
                            System.out.println("---------------------------");
                            int type = Menu.readChoice(i + 2, scanner);

                            if (type == i + 2) {
                                break;
                            }

                            if (type == i + 1) {
                                System.out.println("Enter Designation: ");
                                String designation = scanner.readLine();
                                EmployeeType.addDesignation(handler, designation);
                            }
                            int j = 0;
                            for (j = 0; j < EmployeeDepartment.employeeDepartments.size(); j++) {
                                System.out.printf("%d. %s\n", j + 1, EmployeeDepartment.employeeDepartments.get(j));
                            }
                            System.out.printf("%d. Other\n", j + 1);
                            System.out.printf("%d. Exit\n", j + 2);
                            System.out.println("---------------------------");
                            int dept = Menu.readChoice(j + 2, scanner);

                            if (dept == j + 2) {
                                break;
                            }
                            if (dept == j + 1) {
                                System.out.println("Enter Department: ");
                                String designation = scanner.readLine();
                                EmployeeDepartment.addDepartment(handler, designation);
                            }
                            int id = Employee.validateId(employees, scanner);
                            String name = Employee.validateName(scanner);
                            int age = Employee.validateAge(scanner, 21, 60);
                            System.out.println("Enter salary: ");
                            double salary = Double.parseDouble(scanner.readLine());
                            Employee emp = Employee.getEmployee(id, type, name, age, dept, salary, handler);
                            employees.put(emp.id, emp);
                            count += 1;
                        }
                    }
                    case 2 -> {
                        System.out.println("---------------------------");
                        System.out.println("Display Employee With Respect to: ");
                        System.out.println("---------------------------");
                        System.out.println("1. ID");
                        System.out.println("2. Name");
                        System.out.println("3. Age");
                        System.out.println("4. Designation");
                        System.out.println("5. Department");
                        System.out.println("6. Salary");
                        System.out.println("7. Exit");
                        System.out.println("---------------------------");
                        int type = Menu.readChoice(6, scanner);
                        if (type == 7)
                            break;
                        handler.getEmployeesSorted(type);
                        // System.out.println(sorted.values().toString());
                        // Employee.displayAll(sorted);
                    }
                    case 3 -> {
                        System.out.println("Enter the id of employee to raise salary: ");
                        int id = Employee.validateDeletingId(employees, scanner);
                        Employee emp = employees.get(id);
                        System.out.println("Amount to raise: ");
                        double amount = Double.parseDouble(scanner.readLine());
                        emp.raiseSalary(amount, handler);
                        // for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                        // if (type == 1 && entry.getValue() instanceof Manager)
                        // entry.getValue().raiseSalary();
                        // if (type == 2 && entry.getValue() instanceof Clerk)
                        // entry.getValue().raiseSalary();
                        // if (type == 3 && entry.getValue() instanceof Programmer)
                        // entry.getValue().raiseSalary();
                        // }

                    }
                    case 4 -> {

                        int index = Employee.validateDeletingId(employees, scanner);
                        System.out
                                .println("Employee to delete: " + index);
                        employees.get(index).display();
                        System.out.println(
                                "Are you sure you want to delete this employee? (y/n)");
                        String confirm = "y";
                        try {
                            confirm = scanner
                                    .readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!confirm.equals("y")) {
                            break;
                        }
                        boolean deleted = handler.deleteEmployee(index);
                        if (deleted) {

                            employees.remove(index);
                            System.out.println("Employee Deleted Successfully");
                        } else
                            System.out.println("Something went wrong");
                    }
                    case 5 -> {
                        System.out.println("---------------------------");
                        System.out.println("Search By");
                        System.out.println("---------------------------");
                        System.out.println("1. ID");
                        System.out.println("2. Designation");
                        System.out.println("3. Name");
                        System.out.println("4. Department");
                        System.out.println("5. Exit");
                        System.out.println("---------------------------");
                        int type = Menu.readChoice(4, scanner);
                        if (type == 5)
                            break;
                        if (type == 1) {
                            int search = Employee.validateDeletingId(employees, scanner);
                            employees.get(search)
                                    .display();
                            break;
                        }
                        List<Employee> emp = Employee.searchEmployee(employees, type, scanner, handler);
                        if (emp.size() == 0) {
                            System.out.println("No Employees with the search parameter exists");
                        }
                        for (Employee e : emp) {
                            System.out.println("---------------------");
                            e.display();
                            System.out.println("---------------------");
                        }

                    }
                    case 6 -> {

                        System.out.println("Total employees Existing in the system: " + employees.size());
                        System.out
                                .println("Total Employees Created in the system: " + (count - 1));
                        // scanner.close();
                        System
                                .exit(0);
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                handler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
