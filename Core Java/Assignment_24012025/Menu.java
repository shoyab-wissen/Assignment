package Assignment_24012025;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import Assignment_24012025.IncorrectChoiceException;
import java.util.Scanner;

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
            } catch (IncorrectChoiceException exception) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred");
            }

        } while (choice < 1 || choice > maxChoice);
        return choice;
    }
}
