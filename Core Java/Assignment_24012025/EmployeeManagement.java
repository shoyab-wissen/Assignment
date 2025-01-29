package Assignment_24012025;

import Assignment_24012025.Menu;
import Assignment_24012025.Employee;
import Assignment_24012025.CEO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.List;

public class EmployeeManagement {
    static CEO ceo;
    static {
        ceo = CEO.getObject();
    }

    public static void main(String[] args) {
        FileHandler handler = FileHandler.getObject();
        HashMap<Integer, Employee> employees = handler.readFromObjectStream();
        if (employees.size() < 1) {
            // int success = handler.writeEmployee(ceo);
            employees.put(1, ceo);
            // System.out.println(success);
            // employees.put(1, ceo);
        }
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
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
            try {

                switch (choice) {
                    case 1 -> {
                        while (true) {
                            System.out.println("---------------------------");
                            System.out.println("1. Manager");
                            System.out.println("2. Clerk");
                            System.out.println("3. Programmer");
                            System.out.println("4. Exit");
                            System.out.println("---------------------------");
                            int type = Menu.readChoice(4, scanner);

                            if (type == 4) {
                                break;
                            }
                            int id = Employee.validateId(employees, scanner);

                            String name = Employee.validateName(scanner);
                            int age = Employee.validateAge(scanner, 21, 60);
                            Employee emp = Employee.getEmployee(type, name, id, age);
                            employees.put(id, emp);
                            // handler.writeEmployee(emp);
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
                        System.out.println("5. Salary");
                        System.out.println("6. Exit");
                        System.out.println("---------------------------");
                        int type = Menu.readChoice(6, scanner);
                        if (type == 6)
                            break;
                        Employee.displayAll(employees, type);
                    }
                    case 3 -> {
                        System.out.println("---------------------------");
                        System.out.println("1. Manager");
                        System.out
                                .println("2. Clerk");
                        System.out.println("3. Programmer");
                        System.out
                                .println("4. Exit");
                        System.out.println("---------------------------");
                        int type = Menu
                                .readChoice(4, scanner);
                        if (type == 4)
                            break;
                        Employee emp = null;
                        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                            if (type == 1 && entry.getValue() instanceof Manager) {
                                emp = entry.getValue();
                                emp.raiseSalary();
                            }
                            if (type == 2 && entry.getValue() instanceof Clerk) {
                                emp = entry.getValue();
                                emp.raiseSalary();
                                // handler.raiseSalary(emp);
                            }
                            if (type == 3 && entry.getValue() instanceof Programmer) {
                                emp = entry.getValue();
                                emp.raiseSalary();
                                // handler.raiseSalary(emp);
                            }
                        }

                    }
                    case 4 -> {

                        int index = Employee.validateDeletingId(employees, scanner);
                        System.out
                                .println("Employee to delete: " + index);
                        Employee emp = employees.get(index);
                        emp.display();
                        System.out.println(
                                "Are you sure you want to delete this employee? (y/n)");
                        String confirm = "n";
                        try {
                            confirm = scanner
                                    .readLine();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (!confirm.equals("y")) {
                            break;
                        }
                        // handler.removeEmployee(emp);
                        employees.remove(index);
                        System.out.println("Employee Deleted Successfully");
                    }
                    case 5 -> {
                        System.out.println("---------------------------");
                        System.out.println("Search By");
                        System.out.println("---------------------------");
                        System.out.println("1. ID");
                        System.out.println("2. Designation");
                        System.out.println("3. Name");
                        System.out.println("4. Exit");
                        System.out.println("---------------------------");
                        int type = Menu.readChoice(4, scanner);
                        if (type == 4)
                            break;
                        if (type == 1) {
                            int search = Employee.validateDeletingId(employees, scanner);
                            employees.get(search)
                                    .display();
                            break;
                        }
                        List<Employee> emp = Employee.searchEmployee(employees, type, scanner);
                        if (emp.size() == 0) {
                            System.out.println("No Employees with the search parameter exists");
                        }
                        for (Employee e : Employee.searchEmployee(employees, type, scanner)) {
                            System.out.println("---------------------");
                            e.display();
                            System.out.println("---------------------");
                        }

                    }
                    case 6 -> {

                        System.out.println("Total employees Existing in the system: " + employees.size());
                        System.out
                                .println("Total Employees Created in the system: " + (count - 1));
                        handler.writeObjectToFile(employees);
                        try {
                            handler.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System
                                .exit(0);
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("An Exception has occured");
            } finally {
                handler.writeObjectToFile(employees);
                try {
                    handler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
