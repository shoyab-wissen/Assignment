package emp;

import java.util.Scanner;
import java.util.regex.Pattern;

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

    final static int validateAge(Scanner scanner, int min, int max) {
        int age = 0;
        do {
            try {
                System.out.print("Enter employee age: ");
                age = scanner.nextInt();
                if (age < min || age > max) {
                    throw new InvalidAgeException("Invalid age");
                }
            } catch (InvalidAgeException e) {
                System.out.println("Invalid age. Please enter age between "+ min +" and "+max);
            } catch (Exception e) {
                System.out.println("Please Enter a Number in age feild");
                scanner.next();
                continue;
            }
        } while (age < min || age > max);
        return age;
    }

    final static int validateId(Employee[] employees, Scanner scanner) {
        int id = 0;
        do {
            try {
                System.out.print("Enter Employee Id:");
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
        return id;
    }

    final static int validateDeletingId(Employee[] employees, Scanner scanner) {
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
        return index;
    }

    final static String validateName(Scanner scanner) {
        String name = "";
        Pattern p1 = Pattern.compile("[A-Z].[a-zA-Z]*+\\ [A-Z].[a-zA-Z]*+");
        do {
            try {
                System.out.print("Enter employee name: ");
                name = scanner.nextLine();
                if (name.length() < 5 || name.length() > 20) {
                    throw new InvalidNameException("Invalid name");
                }
                if (!p1.matcher(name).matches()) {
                    throw new InvalidNameException("Invalid name");
                }
                break;
            } catch (InvalidNameException e) {
                System.out.println(
                        "Invalid name.\nName Should start with Capital letter\nName Should have 2 words\nName Should contain atleast 5 charaters and atmost 20 characters");
            } catch (Exception e) {
                System.out.println("Please Enter a valid name");
                scanner.next();
                continue;
            }
        } while (true);
        return name;
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