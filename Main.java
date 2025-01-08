import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum EmployeeType {
    CLERK,
    PROGRAMMER,
    MANAGER
}

class Employee {
    private int id;
    static List<Employee> employee = new ArrayList<>();
    private String ename;
    private EmployeeType designation;
    private int age;
    private double salary;
    private String password;

    Employee(String ename, EmployeeType designation, int age, double salary, String password) {
        if (Employee.employee.size() == 0) {
            this.id = 1;

        } else {
            this.id = Employee.employee.get(Employee.employee.size() - 1).id + 1;
        }
        this.ename = ename;
        this.designation = designation;
        this.age = age;
        this.salary = salary;
        this.password = password;
    }

    int getId() {
        return id;
    }

    String getDesignation() {
        return designation.name();
    }

    String getPassword() {
        return password;
    }

    String display() {
        String data = "---------------------------------------------------------------------\nEmployee ID: " + this.id
                + "\nEmployee Name: " + this.ename + "\nAge: " + this.age + "\nSalary: " + this.salary + "\nPassword: "
                + this.password + "\nEmployee Type: " + designation.name()
                + "\n---------------------------------------------------------------------";
        return data;
    }

    void raiseSalary(double increment) {
        this.salary += increment;
    }
}

public class Main {
    public static void main(String[] args) {
        // Employee e1 = new Employee("Shoyab", "Software Developer", 22,
        // EmployeeType.PROGRAMMER, 99999999999999999.99);
        // System.out.println(e1.display());
        // e1.raiseSalary(10000000000000000000000000000000.00);
        // System.out.println(e1.display());
        Employee e1 = new Employee("Shoyab", EmployeeType.MANAGER, 22, 9999999999999999999999999999999999.0,
                "TestPass");
        Employee.employee.add(e1);
        while (true) {
            System.out.println("1. Add Employee");
            System.out.println("2. Display Single Employee");
            System.out.println("3. Display All Employee");
            System.out.println("4. Raise Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Employee Name");
                    String ename = sc.next();
                    System.out.println("Enter Employee Age");
                    int age = sc.nextInt();
                    System.out.println("Enter Employee Type");
                    System.out.println("1. Manager");
                    System.out.println("2. Programmer");
                    System.out.println("3. Clerk");
                    int intType = sc.nextInt();
                    EmployeeType type;
                    if (intType == 1) {
                        type = EmployeeType.MANAGER;
                    } else if (intType == 2) {
                        type = EmployeeType.PROGRAMMER;
                    } else if (intType == 3) {
                        type = EmployeeType.CLERK;
                    } else {
                        System.out.println("Invalid Input Setting Default Value as Clerk");
                        type = EmployeeType.CLERK;
                    }
                    System.out.println("Enter Employee Salary");
                    double salary = sc.nextDouble();
                    System.out.println("Enter Password for user: ");
                    String pass = sc.next();
                    Employee e2 = new Employee(ename, type, age, salary, pass);
                    Employee.employee.add(e2);
                    break;
                case 2:
                    System.out.println("Enter ID of employee: ");
                    int id = sc.nextInt();
                    boolean found = false;
                    for (Employee e : Employee.employee) {
                        if (e.getId() == id) {
                            System.out.println(e.display());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Employee not found");
                    }
                    break;

                case 3:
                    for (Employee e : Employee.employee) {
                        System.out.println(e.display());
                    }
                    break;
                case 4:
                    System.out.println("Enter Your Employee ID");
                    int id1 = sc.nextInt();
                    for (Employee e : Employee.employee) {
                        if (e.getId() == id1) {
                            if (e.getDesignation() != EmployeeType.MANAGER.name()) {
                                System.out.println("You are not a manager");
                                break;
                            }
                            System.out.println("Enter Your Password");
                            String pass1 = sc.next();
                            System.out.println(pass1);
                            System.out.println(e.toString());
                            if (pass1.equals(e.getPassword())) {
                                System.out.println("Enter ID of the employee");
                                id = sc.nextInt();
                                System.out.println("Enter Raise Amount");
                                double ra = sc.nextDouble();
                                found = false;
                                for (Employee emp : Employee.employee) {
                                    if (emp.getId() == id) {
                                        emp.raiseSalary(ra);
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Employee not found");
                                }
                            } else {
                                System.out.println("Invalid Password");
                            }
                            break;
                        }
                    }

                    break;

                case 5:
                    System.out.println("Enter ID of employee: ");
                    id = sc.nextInt();
                    found = false;
                    for (Employee e : Employee.employee) {
                        if (e.getId() == id) {
                            Employee.employee.remove(e);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Employee not found");
                    }
                    break;
                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
                    break;

            }

        }

    }
}
