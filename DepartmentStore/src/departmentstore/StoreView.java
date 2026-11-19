/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Gicely
 */
public class StoreView {
    
    private final Scanner scanner = new Scanner(System.in);

    public void message(String text) {
        System.out.println(text);
    }

    public String read(String prompt) {

        System.out.print(prompt);

        if (!scanner.hasNextLine()) {
            throw new NoSuchElementException();
        }
        return scanner.nextLine().trim();
    }

    //display numbered choices and accept only a valid number
    private int chooseNumber(String heading, String[] labels) {

        while (true) {

            message("\n" + heading);

            for (int i = 0; i < labels.length; i++) {
                message((i + 1) + ". " + labels[i]);
            }

            try {
                int number = Integer.parseInt(read("Choose an option: "));

                if (number >= 1 && number <= labels.length) {
                    return number - 1;
                }
            //invalid text returns to the same question.
            } catch (NumberFormatException ex) {    
            }
            message("Invalid option. Please try again.");
        }
    }

    public MenuOption chooseMainMenu() {

        MenuOption[] options = MenuOption.values();

        String[] labels = new String[options.length];

        //build the menu by iterating through the enum values
        for (int i = 0; i < options.length; i++) {
            labels[i] = options[i].toString();
        }

        int index = chooseNumber("Main menu", labels);

        return options[index];
    }

    public ManagerType chooseManagerType() {

        ManagerType[] options = ManagerType.values();

        String[] labels = new String[options.length];

        for (int i = 0; i < options.length; i++) {
            labels[i] = options[i].name().replace('_', ' ');
        }

        int index = chooseNumber("Manager type / role", labels);

        return options[index];
    }

    public Department chooseDepartment() {

        Department[] options = Department.values();

        String[] labels = new String[options.length];

        for (int i = 0; i < options.length; i++) {
            labels[i] = options[i].name().replace('_', ' ');
        }

        int index = chooseNumber("Existing department", labels);

        return options[index];
    }

    public void showRecords(Employee[] records) {

        for (int i = 0; i < records.length; i++) {
            message((i + 1) + ". " + records[i]);
        }
    }

    public void close() {
        scanner.close();
    }
    
}
