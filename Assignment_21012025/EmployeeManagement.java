package Assignment_21012025;

import Assignment_21012025.Menu;
import Assignment_21012025.Employee;
import Assignment_21012025.CEO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeManagement {
    static CEO ceo;
    static {
        ceo = CEO.getObject();
    }

    public static void main(String[] args) {
        HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();

        employees.put(1, ceo);
        Scanner scanner = new Scanner(System.in);
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

                        if (type == 1) {
                            employees.put(id, Employee.getEmployee(EmployeeType.MANAGER, name, id, age));
                        } else if (type == 2) {
                            employees.put(id, Employee.getEmployee(EmployeeType.CLERK, name, id, age));
                        } else {
                            employees.put(id, Employee.getEmployee(EmployeeType.PROGRAMMER, name, id, age));
                        }

                        count += 1;
                    }
                }
                case 2 -> Employee.displayAll(employees);
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
                    for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                        if (type == 1 && entry.getValue() instanceof Manager)
                            entry.getValue().raiseSalary();
                        if (type == 2 && entry.getValue() instanceof Clerk)
                            entry.getValue().raiseSalary();
                        if (type == 3 && entry.getValue() instanceof Programmer)
                            entry.getValue().raiseSalary();
                    }

                }
                case 4 -> {

                    int index = Employee.validateDeletingId(employees, scanner);
                    System.out
                            .println("Employee to delete: " + index);
                    employees.get(index).display();
                    System.out.println(
                            "Are you sure you want to delete this employee? (y/n)");
                    String confirm = scanner
                            .next();
                    if (!confirm.equals("y")) {
                        break;
                    }

                    employees.remove(index);
                    System.out.println("Employee Deleted Successfully");
                }
                case 5 -> {
                    int search = Employee.validateDeletingId(employees, scanner);
                    employees.get(search)
                            .display();
                }
                case 6 -> {

                    System.out.println("Total employees Existing in the system: " + employees.size());
                    System.out
                            .println("Total Employees Created in the system: " + (count - 1));
                    scanner.close();
                    System
                            .exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
