package com.management.employee;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


enum EmployeeType {
    CLERK,
    PROGRAMMER,
    MANAGER,
    CEO
}

public abstract class Employee {
    String name;
    int id;
    int age;
    EmployeeType designation;
    double salary;

    Employee(String name, int id, int age, EmployeeType designation) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.designation = designation;
    }

    public static Employee getEmployee(int type, String name, int id, int age) {
        switch (type) {
            case 1:
                return Manager.getEmployee(name, id, age);
            case 2:
                return Clerk.getEmployee(name, id, age);
            case 3:
                return Manager.getEmployee(name, id, age);
            default:
                return null;
        }
    }

    abstract void raiseSalary();

    final static void displayAll(HashMap<Integer, Employee> employees, int sortType) {
        HashMap<Integer, Employee> sorted = new HashMap<>();
        sorted.putAll(employees);
        List<Employee> empList = new ArrayList<>();
        empList.addAll(employees.values().stream().toList());
//        Comparator<Employee> comparator = switch (sortType) {
//            case 1 -> new IDSort();
//            case 2 -> new NameSort();
//            case 3 -> new AgeSort();
//            case 4 -> new DesignationSort();
//            case 5 -> new SalarySort();
//            default -> new IDSort();
//        };
//        empList.sort(comparator);
        Iterator<Map.Entry<Integer, Employee>> iter = employees.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Employee> value = iter.next();
            System.out.println("---------------------------");
            System.out.println(
                    "Employee ID: " + value.getValue().id + "\nEmployee Name: " + value.getValue().name + "\nAge: "
                            + value
                                    .getValue().age
                            + "\nEmployee Type: "
                            + value.getValue().designation.name() + "\nSalary: "
                            + (value.getValue() instanceof Manager ? ((Manager) value.getValue()).salary
                                    : value.getValue() instanceof Clerk ? ((Clerk) value.getValue()).salary
                                            : value.getValue() instanceof CEO ? "NA"
                                                    : ((Programmer) value.getValue()).salary));
            System.out.println("---------------------------");
        }
    }

    final void display() {
        System.out.println("---------------------------");
        System.out.println(
                "Employee ID: " + this.id + "\nEmployee Name: " + this.name + "\nAge: " + this.age + "\nEmployee Type: "
                        + designation.name() + "\nSalary: " + (this instanceof Manager ? ((Manager) this).salary
                                : this instanceof Clerk ? ((Clerk) this).salary
                                        : this instanceof CEO ? "NA" : ((Programmer) this).salary));
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
                System.out.println("Invalid age. Please enter age between " + min + " and " + max);
            } catch (Exception e) {
                System.out.println("Please Enter a Number in age feild");
                scanner.next();
                continue;
            }
        } while (age < min || age > max);
        return age;
    }

    final static int validateId(HashMap<Integer, Employee> employees, Scanner scanner) {
        int id = 0;
        do {
            try {
                System.out.print("Enter Employee Id:");
                id = scanner.nextInt();
                if (id <= 0) {
                    throw new InvalidIdException("Invalid id");

                }
                // for (int i = 0; i < employees.length; i++) {
                // if (employees[i] != null && employees[i].id == id) {
                // }
                // }
                if (employees.containsKey(id))
                    throw new IdAlreadyExistsException("Id already exists");
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

    final static int validateDeletingId(HashMap<Integer, Employee> employees, Scanner scanner) {
        int deleteId = 0;
        do {
            try {
                System.out.println("Enter Employee Id to delete:");
                deleteId = scanner.nextInt();
                if (!employees.containsKey(deleteId)) {
                    throw new IdNotFoundException();
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
        return deleteId;
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

    final static List<Employee> searchEmployee(HashMap<Integer, Employee> employees, int type, Scanner sc) {
        System.out.println("Enter the search key: ");
        sc.nextLine();
        String searchPara = sc.nextLine();
        List<Employee> emp = employees.values().stream().toList();
        List<Employee> searchResult = new ArrayList<>();
        for (Employee e : emp) {
            if (type == 2) {
                if (searchPara.toLowerCase().equals(e.designation.toString().toLowerCase())) {
                    searchResult.add(e);
                }
            }
            if (type == 3) {
                if (searchPara.toLowerCase().equals(e.name.toLowerCase())) {
                    searchResult.add(e);
                }
            }
        }
        return searchResult;
    }
}
