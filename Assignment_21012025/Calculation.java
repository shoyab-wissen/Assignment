package Assignment_21012025;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import javax.naming.NameNotFoundException;

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }
}

public class Calculation {
    public static void main(String[] args) {
        System.out.print("Enter the class u want to perform operations on: ");
        Scanner sc = new Scanner(System.in);
        String className = sc.nextLine();
        try {
            Class class1 = Class.forName(className);
            Method[] methods = class1.getDeclaredMethods();
            boolean foundMethod = false;
            System.out.println("These are the Methods You can call");
            for (Method method : methods) {
                System.out.println(method.getName());
            }
            System.out.print("Enter the Operation You want to perform: ");
            String methodName = sc.nextLine();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    foundMethod = true;
                    Parameter[] parameters = method.getParameters();
                    Class[] paraType = method.getParameterTypes();
                    Object obj = class1.newInstance();
                    Object[] paraInput = new Object[parameters.length];
                    for (int i = 0; i < paraInput.length; i++) {
                        if (paraType[i].isPrimitive()) {
                            System.out.print("Enter the Number: ");
                            paraInput[i] = sc.nextInt();
                        }
                    }
                    System.out.println(method.invoke(obj, paraInput));
                    break;
                }
            }
            if (!foundMethod)
                throw new NameNotFoundException();
        } catch (ClassNotFoundException e) {
            System.out.println("The class u mention does not exist");
        } catch (NameNotFoundException e) {
            System.out.println("Method Does not exist");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
