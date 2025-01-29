package Assignment_16012025;

import Assignment_16012025.Menu;
import Assignment_16012025.Employee;
import Assignment_16012025.CEO;
import java.util.Scanner;

public class EmployeeManagement {
    static CEO ceo;
    static {
        ceo = CEO.getObject();
    }

    public static void main(String[] args) {
        Employee[] employees = new Employee[100];
        employees[0] = ceo;
        Scanner scanner = new Scanner(System.in);
        int count = 1;
        while (true) {
            // try {
            System.out.println("---------------------------");
            System.out.println("1. Create");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.println("---------------------------");
            System.out.print("Enter your choice: ");
            int choice = Menu.readChoice(5, scanner);
            switch (choice) {
                case 1:
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
                            employees[count] = Employee.getEmployee(EmployeeType.MANAGER, name, id, age);
                        } else if (type == 2) {
                            employees[count] = Employee.getEmployee(EmployeeType.CLERK, name, id, age);
                        } else {
                            employees[count] = Employee.getEmployee(EmployeeType.PROGRAMMER, name, id, age);
                        }

                        count += 1;
                    }

                    break;
                case 2:
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i] != null) {
                            employees[i].display();
                        }
                    }
                    break;
                case 3:
                    System.out.println("---------------------------");
                    System.out.println("1. Manager");
                    System.out.println("2. Clerk");
                    System.out.println("3. Programmer");
                    System.out.println("4. Exit");
                    System.out.println("---------------------------");
                    int type = Menu.readChoice(4, scanner);
                    if (type == 1) {
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] instanceof Manager) {
                                employees[i].raiseSalary();
                            }
                        }
                        System.out.println("Salary of all Managers raised by 10000");
                    } else if (type == 2) {
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] instanceof Clerk) {
                                employees[i].raiseSalary();
                            }
                        }
                        System.out.println("Salary of all Clerks raised by 2000");
                    } else if (type == 3) {
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] instanceof Programmer) {
                                employees[i].raiseSalary();
                            }
                        }
                        System.out.println("Salary of all Programmers raised by 5000");
                    } else if (type == 4) {
                        break;
                    }

                    break;
                case 4:

                    int index = Employee.validateDeletingId(employees, scanner);
                    System.out.println("Employee to delete: " + index);
                    employees[index].display();
                    System.out.println("Are you sure you want to delete this employee? (y/n)");
                    String confirm = scanner.next();
                    if (!confirm.equals("y")) {
                        break;
                    }
                    if (employees[index] != null) {
                        employees[index] = null;
                        System.out.println("Employee deleted");
                    } else {
                        System.out.println("Employee not found");
                    }
                    break;
                case 5:
                    int empCount = 0;
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i] != null) {
                            empCount++;
                        }
                    }
                    System.out.println("Total employees Existing in the system: " + empCount);
                    System.out.println("Total Employees Created in the system: " + (count - 1));
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
