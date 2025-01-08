import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum EmployeeType {
    CLERK,
    PROGRAMMER,
    MANAGER
}

class Employee {
    static List<Employee> employee = new ArrayList<>();
    private String ename;
    private EmployeeType designation;
    private int age;
    private static double[] salary = { 20000, 30000, 100000 };

    Employee(String ename, int age, EmployeeType designation) {
        this.ename = ename;
        this.age = age;
        this.designation = designation;
    }

    String display() {
        double currSalary = designation == EmployeeType.CLERK ? salary[0]
                : designation == EmployeeType.PROGRAMMER ? salary[1] : salary[2];
        String data = "---------------------------------------------------------------------" + "\nEmployee Name: "
                + this.ename + "\nAge: " + this.age + "\nSalary: " + currSalary + "\nEmployee Type: "
                + designation.name() + "\n---------------------------------------------------------------------";
        return data;
    }

    static void raiseSalary(EmployeeType type) {
        switch (type) {
            case CLERK:
                salary[0] += 2000;
                break;
            case MANAGER:
                salary[2] += 15000;
                break;
            case PROGRAMMER:
                salary[1] += 5000;
                break;
            default:
                break;
        }
    }
}

public class EmployeeManagementApp {
    public static void main(String[] args) {
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("1. Create");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.println("--------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Choice :- ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("--------------------------------");
                        System.out.println("1. Clerk");
                        System.out.println("2. Programmer");
                        System.out.println("3. Manager");
                        System.out.println("4. Exit");
                        System.out.println("--------------------------------");
                        System.out.println("Enter Choice :- ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        EmployeeType type;
                        if (choice == 1) {
                            type = EmployeeType.CLERK;
                        } else if (choice == 2) {
                            type = EmployeeType.PROGRAMMER;
                        } else if (choice == 3) {
                            type = EmployeeType.MANAGER;
                        } else if (choice == 4) {
                            break;
                        } else {
                            System.out.println("Setting Default Employee Type to CLERK");
                            type = EmployeeType.CLERK;
                        }
                        System.out.println("Enter Employee Name");
                        String ename = sc.nextLine();
                        System.out.println("Enter Employee Age");
                        int age = sc.nextInt();
                        Employee e2 = new Employee(ename, age, type);
                        Employee.employee.add(e2);
                    }

                    break;

                case 2:
                    for (Employee e : Employee.employee) {
                        System.out.println(e.display());
                    }
                    break;
                case 3:
                    System.out.println("Raise Salary of:");
                    System.out.println("--------------------------------");
                    System.out.println("1. Clerk");
                    System.out.println("2. Programmer");
                    System.out.println("3. Manager");
                    System.out.println("--------------------------------");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        Employee.raiseSalary(EmployeeType.CLERK);
                    } else if (choice == 2) {
                        Employee.raiseSalary(EmployeeType.PROGRAMMER);
                    } else if (choice == 3) {
                        Employee.raiseSalary(EmployeeType.MANAGER);
                    } else if (choice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid Choice");
                    }
                    break;

                case 4:
                    System.out.println("Total no. of employees created/added : " + Employee.employee.size());
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
                    break;

            }

        }

    }
}
