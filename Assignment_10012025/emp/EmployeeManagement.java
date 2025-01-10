package emp;

import java.util.Scanner;

enum EmployeeType {
    CLERK,
    PROGRAMMER,
    MANAGER
}

abstract class Employee {
    String name;
    int id;
    int age;
    EmployeeType designation;

    Employee(String name, int id, int age, EmployeeType designation) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.designation = designation;
    }

    abstract void raiseSalary();

    final void display() {
        System.out.println("---------------------------");
        System.out.println(
                "Employee ID: " + this.id + "\nEmployee Name: " + this.name + "\nAge: " + this.age + "\nEmployee Type: "
                        + designation.name() + "\nSalary: " + (this instanceof Manager ? ((Manager) this).salary
                                : this instanceof Clerk ? ((Clerk) this).salary : ((Programmer) this).salary));
        System.out.println("---------------------------");
    }
}

final class Manager extends Employee {
    double salary;

    Manager(String name, int id, int age) {
        super(name, id, age, EmployeeType.MANAGER);
        this.salary = 100000;
    }

    void raiseSalary() {
        this.salary += 10000;
    }

}

final class Clerk extends Employee {
    double salary;

    Clerk(String name, int id, int age) {
        super(name, id, age, EmployeeType.CLERK);
        this.salary = 10000;
    }

    void raiseSalary() {
        this.salary += 2000;
    }
}

final class Programmer extends Employee {
    double salary;

    Programmer(String name, int id, int age) {
        super(name, id, age, EmployeeType.PROGRAMMER);
        this.salary = 50000;
    }

    void raiseSalary() {
        this.salary += 5000;
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        Employee[] employees = new Employee[100];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------------");
            System.out.println("1. Create");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.println("---------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("---------------------------");
                        System.out.println("1. Manager");
                        System.out.println("2. Clerk");
                        System.out.println("3. Programmer");
                        System.out.println("4. Exit");
                        System.out.println("---------------------------");
                        System.out.print("Enter employee type: ");
                        int type = scanner.nextInt();
                        if (type == 4) {
                            break;
                        }
                        System.out.print("Enter employee name: ");
                        String name = scanner.next();
                        int emptyId = -1;
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] == null) {
                                emptyId = i;
                                break;
                            }
                        }
                        System.out.print("Enter employee age: ");
                        int age = scanner.nextInt();
                        if (type == 1) {
                            employees[emptyId] = new Manager(name, emptyId, age);
                        } else if (type == 2) {
                            employees[emptyId] = new Clerk(name, emptyId, age);
                        } else if (type == 3) {
                            employees[emptyId] = new Programmer(name, emptyId, age);
                        } else {
                            System.out.println("Invalid choice");
                        }
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
                    System.out.print("Enter employee type: ");
                    int type = scanner.nextInt();
                    if (type == 1) {
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] instanceof Manager) {
                                employees[i].raiseSalary();
                            }
                        }
                    } else if (type == 2) {
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] instanceof Clerk) {
                                employees[i].raiseSalary();
                            }
                        }
                    } else if (type == 3) {
                        for (int i = 0; i < employees.length; i++) {
                            if (employees[i] instanceof Programmer) {
                                employees[i].raiseSalary();
                            }
                        }
                    } else if (type == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice");
                    }
                case 4:
                    System.out.print("Enter employee id: ");
                    int deleteId = scanner.nextInt();
                    employees[deleteId].display();
                    System.out.println("Are you sure you want to delete this employee? (y/n)");
                    String confirm = scanner.next();
                    if (!confirm.equals("y")) {
                        break;
                    }
                    if (employees[deleteId] != null) {
                        employees[deleteId] = null;
                        System.out.println("Employee deleted");
                    } else {
                        System.out.println("Employee not found");
                    }
                    break;
                case 5:
                    int count = 0;
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i] != null) {
                            count++;
                        }
                    }
                    System.out.println("Total employees Existing in the system: " + count);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
}
