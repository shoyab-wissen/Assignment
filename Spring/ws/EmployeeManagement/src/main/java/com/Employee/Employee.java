package com.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class EmployeeType {
    static List<String> employeeTypes = new ArrayList<>();

    public static void getDesignation(DatabaseHandler handler) {
        employeeTypes = handler.getDesignation();
    }

    public static void addDesignation(DatabaseHandler handler, String other) {
        boolean added = handler.addDesignation(other);
        if (added) {
            getDesignation(handler);
        }
    }
}

class EmployeeDepartment {
    static List<String> employeeDepartments = new ArrayList<>();

    public static void getDepartments(DatabaseHandler handler) {
        employeeDepartments = handler.getDepatments();
    }

    public static void addDepartment(DatabaseHandler handler, String other) {
        boolean added = handler.addDepartment(other);
        if (added) {
            getDepartments(handler);
        }
    }
}

public class Employee {
    String name;
    int id;
    int age;
    String designation;
    double salary;
    String department;

    Employee(int id, String name, int age, String designation, String department, double salary) {
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.department = department;
        this.salary = salary;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", age=" + age + ", designation=" + designation + ", salary="
                + salary + ", department=" + department + "]";
    }

    protected static Employee getEmployee(int id, int type, String name, int age, int department, double salary,
            DatabaseHandler handler) {
        Employee emp = new Employee(id, name, age, EmployeeType.employeeTypes.get(type - 1),
                EmployeeDepartment.employeeDepartments.get(department - 1), salary);
        handler.insertEmployee(emp);
//        emp.id = id;
        return emp;
    }

    void raiseSalary(double increment, DatabaseHandler handler) {
        boolean updated = handler.raiseSalary(this.id, increment);
        if (updated)
            System.out.println("Updated salary successfully of " + this.name);
        else
            System.out.println("Something went wrong");
    }

    final static void displayAll(HashMap<Integer, Employee> employees) {
        // HashMap<Integer, Employee> sorted = new HashMap<>();
        // sorted.putAll(employees);
        // List<Employee> empList = new ArrayList<>();
        // empList.addAll(employees.values().stream().toList());
        // Comparator<Employee> comparator = switch (sortType) {
        // case 1 -> new IDSort();
        // case 2 -> new NameSort();
        // case 3 -> new AgeSort();
        // case 4 -> new DesignationSort();
        // case 5 -> new SalarySort();
        // default -> new IDSort();
        // };
        // empList.sort(comparator);
        List<Employee> emp = employees.values().stream().toList();
        for (int i = 0; i < emp.size(); i++) {
            Employee value = emp.get(i);
            System.out.println("---------------------------");
            System.out.println(
                    "Employee ID: " + value.id + "\nEmployee Name: " + value.name + "\nAge: "
                            + value.age
                            + "\nEmployee Type: "
                            + value.designation
                            + "\nDepartment: "
                            + value.department
                            + "\nSalary: "
                            + value.salary);
            System.out.println("---------------------------");
        }
    }

    final void display() {
        System.out.println("---------------------------");
        System.out.println(
                "Employee ID: " + this.id + "\nEmployee Name: " + this.name + "\nAge: " + this.age + "\nEmployee Type: "
                        + designation + "\nDepartment: " + this.department + "\nSalary: " + this.salary);
        System.out.println("---------------------------");
    }

    final static int validateAge(BufferedReader scanner, int min, int max) {
        int age = 0;
        do {
            try {
                System.out.print("Enter employee age: ");
                age = Integer.parseInt(scanner.readLine());
                if (age < min || age > max) {
                    throw new InvalidAgeException("Invalid age");
                }
            } catch (InvalidAgeException e) {
                System.out.println("Invalid age. Please enter age between " + min + " and " + max);
            } catch (Exception e) {
                System.out.println("Please Enter a Number in age feild");
                continue;
            }
        } while (age < min || age > max);
        return age;
    }

    final static int validateId(HashMap<Integer, Employee> employees, BufferedReader scanner) {
        int id = 0;
        do {
            try {
                System.out.print("Enter Employee Id:");
                id = Integer.parseInt(scanner.readLine());
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
                
                continue;
            }
        } while (true);
        return id;
    }

    final static int validateDeletingId(HashMap<Integer, Employee> employees, BufferedReader scanner) {
        int deleteId = 0;
        int index = 0;
        do {
            try {
                System.out.println("Enter Employee Id:");
                deleteId = Integer.parseInt(scanner.readLine());
                if (!employees.containsKey(deleteId)) {
                    throw new IdNotFoundException();
                }
                break;
            } catch (IdNotFoundException e) {
                System.out.println("Id not found. Please enter a different id");
                continue;
            } catch (Exception e) {
                System.out.println("Please Enter a Number in id feild");
                continue;
            }
        } while (true);
        return deleteId;
    }

    final static String validateName(BufferedReader scanner) {
        String name = "";
        Pattern p1 = Pattern.compile("[A-Z].[a-zA-Z]*+\\ [A-Z].[a-zA-Z]*+");
        do {
            try {
                System.out.print("Enter employee name: ");
                name = scanner.readLine();
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
                continue;
            }
        } while (true);
        return name;
    }

    final static List<Employee> searchEmployee(HashMap<Integer, Employee> employees, int type, BufferedReader sc,
            DatabaseHandler handler) {
        System.out.println("Enter the search key: ");
        String searchPara = "";
        try {
            searchPara = sc.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Employee> emp = employees.values().stream().toList();
        HashMap<Integer, Employee> searchResult = handler.getEmployees(type, searchPara);
        // for (Employee e : emp) {
        // if (type == 2) {
        // if (searchPara.toLowerCase().equals(e.designation.toString().toLowerCase()))
        // {
        // searchResult.add(e);
        // }
        // }
        // if (type == 3) {
        // if (searchPara.toLowerCase().equals(e.name.toLowerCase())) {
        // searchResult.add(e);
        // }
        // }
        // }
        return searchResult.values().stream().toList();
    }
}

class NameSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.name.compareTo(o2.name);
    }

}

class DesignationSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.designation.toString().compareTo(o2.designation.toString());
    }

}

class IDSort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.valueOf(o1.id).compareTo(o2.id);
    }
}

class AgeSort implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.valueOf(o1.age).compareTo(o2.age);
    }
}

class SalarySort implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.valueOf(o1.salary).compareTo(o2.salary);
    }

}

// final class Manager extends Employee {

// private Manager(String name, int id, int age) {
// super(name, id, age, EmployeeType.MANAGER);
// this.salary = 100000;
// }

// static protected Manager getEmployee(String name, int id, int age) {
// return new Manager(name, id, age);
// }

// void raiseSalary() {
// this.salary += 10000;
// }

// }

// final class CEO extends Employee {
// private static CEO obj = null;

// private CEO(String name, int id, int age) {
// super(name, id, age, EmployeeType.CEO);
// System.out.println("CEO Object created");
// }

// void raiseSalary() {
// System.out.println("Salary of CEO cannot be raised");
// }

// public static CEO getObject() {
// if (obj == null) {
// obj = new CEO("Shoyab", 60, 1000);
// obj.salary = 1000000;
// }
// return obj;
// }
// }

// final class Clerk extends Employee {

// private Clerk(String name, int id, int age) {
// super(name, id, age, EmployeeType.CLERK);
// this.salary = 10000;
// }

// protected static Clerk getEmployee(String name, int id, int age) {
// return new Clerk(name, id, age);
// }

// void raiseSalary() {
// this.salary += 2000;
// }
// }

// final class Programmer extends Employee {
// double salary;

// private Programmer(String name, int id, int age) {
// super(name, id, age, EmployeeType.PROGRAMMER);
// this.salary = 50000;
// }

// protected static Programmer getEmployee(String name, int id, int age) {
// return new Programmer(name, id, age);
// }

// void raiseSalary() {
// this.salary += 5000;
// }
// }