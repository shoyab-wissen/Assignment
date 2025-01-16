package Assignment_16012025;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static int readChoice(int maxChoice, Scanner sc) {
        int choice = 0;
        do {
            try {
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
                if (choice > maxChoice || choice < 1) {
                    throw new IncorrectChoiceException("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                sc.nextLine();
            } catch (IncorrectChoiceException exception) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice);
            }

        } while (choice < 1 || choice > maxChoice);
        return choice;
    }
}
