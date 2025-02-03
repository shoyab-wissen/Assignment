package com.Employee;


import java.io.BufferedReader;
import java.util.InputMismatchException;

public class Menu {
    public static int readChoice(int maxChoice, BufferedReader sc) {
        int choice = 0;
        do {
            try {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(sc.readLine());
                if (choice > maxChoice || choice < 1) {
                    throw new IncorrectChoiceException("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                // sc.nextLine();
            } catch (IncorrectChoiceException exception) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice);
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }

        } while (choice < 1 || choice > maxChoice);
        return choice;
    }
}
