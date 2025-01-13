package emp;

import java.util.Scanner;
import emp.IdAlreadyExistsException;
import emp.IdNotFoundException;
import emp.IncorrectChoiceException;
import emp.InvalidAgeException;
import emp.InvalidIdException;

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
        int count = 1;
        while (true) {
            try {
                System.out.println("---------------------------");
                System.out.println("1. Create");
                System.out.println("2. Display");
                System.out.println("3. Raise Salary");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.println("---------------------------");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                if (choice > 5 || choice < 1) {
                    throw new IncorrectChoiceException("Invalid choice");
                }
                switch (choice) {
                    case 1:
                        while (true) {
                            try {
                                System.out.println("---------------------------");
                                System.out.println("1. Manager");
                                System.out.println("2. Clerk");
                                System.out.println("3. Programmer");
                                System.out.println("4. Exit");
                                System.out.println("---------------------------");
                                System.out.print("Enter employee type: ");
                                int type = scanner.nextInt();
                                if (type > 4 || type < 1) {
                                    throw new IncorrectChoiceException("Invalid choice");
                                }
                                if (type == 4) {
                                    break;
                                }
                                int id = 0;
                                do {
                                    try {
                                        System.out.println("Enter Employee Id:");
                                        id = scanner.nextInt();
                                        if (id <= 0) {
                                            throw new InvalidIdException("Invalid id");

                                        }
                                        for (int i = 0; i < employees.length; i++) {
                                            if (employees[i] != null && employees[i].id == id) {
                                                throw new IdAlreadyExistsException("Id already exists");
                                            }
                                        }
                                        break;
                                    } catch (InvalidIdException e) {
                                        System.out.println("Invalid id. Id must be a positive integer greater than 0");
                                        continue;
                                    } catch (IdAlreadyExistsException e) {
                                        System.out.println("Id already exists. Please enter a different id");
                                        continue;
                                    } catch (Exception e) {
                                        System.out.println("Please Enter a Number in id feild");
                                        scanner.next();
                                        continue;
                                    }
                                } while (true);

                                System.out.print("Enter employee name: ");
                                String name = scanner.next();
                                int age = 0;
                                do {
                                    try {
                                        System.out.print("Enter employee age: ");
                                        age = scanner.nextInt();
                                        if (age < 21 || age > 60) {
                                            throw new InvalidAgeException("Invalid age");
                                        }
                                    } catch (InvalidAgeException e) {
                                        System.out.println("Invalid age. Please enter age between 21 and 60");
                                    } catch (Exception e) {
                                        System.out.println("Please Enter a Number in age feild");
                                        scanner.next();
                                        continue;
                                    }
                                } while (age < 21 || age > 60);
                                if (type == 1) {
                                    employees[count] = new Manager(name, id, age);
                                } else if (type == 2) {
                                    employees[count] = new Clerk(name, id, age);
                                } else {
                                    employees[count] = new Programmer(name, id, age);
                                }
                                count += 1;
                            } catch (IncorrectChoiceException e) {
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
                        try {
                            System.out.print("Enter employee type: ");
                            int type = scanner.nextInt();
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
                            } else {
                                throw new IncorrectChoiceException("Invalid choice");
                            }
                        } catch (IncorrectChoiceException e) {
                            System.out.println("Invalid choice");
                        } catch (Exception e) {
                            System.out.println("Please Enter a Number in choice feild");
                            scanner.next();
                            continue;
                        }
                        break;
                    case 4:
                        int deleteId = 0;
                        int index = 0;
                        do {
                            try {
                                System.out.println("Enter Employee Id to delete:");
                                deleteId = scanner.nextInt();
                                for (int i = 0; i < employees.length; i++) {
                                    if (employees[i] != null && employees[i].id == deleteId) {
                                        index = i;
                                        break;
                                    }
                                    if (i == employees.length - 1) {
                                        throw new IdNotFoundException("Id not found");
                                    }
                                }
                                break;
                            } catch (IdNotFoundException e) {
                                System.out.println("Id not found. Please enter a different id");
                                continue;
                            } catch (Exception e) {
                                System.out.println("Please Enter a Number in id feild");
                                scanner.next();
                                continue;
                            }
                        } while (true);
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
            } catch (IncorrectChoiceException e) {
                System.out.println("Invalid choice");
            } catch (Exception e) {
                System.out.println("Please Enter a Number in choice feild");
                scanner.next();
                continue;
            } finally {

            }
        }
    }
}
