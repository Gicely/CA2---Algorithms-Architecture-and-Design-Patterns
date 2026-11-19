/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 *
 * @author Gicely
 */
public class StoreController {
    
    private final StoreModel model;
    private final StoreView view;

    public StoreController(StoreModel model,StoreView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {

        try {
            view.message("Department Store Employee System");

            loadFile();

            boolean running = true;

            while (running) {

                MenuOption option = view.chooseMainMenu();

                //connect each menu option to its operation
                switch (option) {

                    case SORT:
                        showSortedNames();
                        break;

                    case SEARCH:
                        searchEmployee();
                        break;

                    case ADD_RECORDS:
                        addRecord();
                        break;

                    case CREATE_TREE:
                        showTree();
                        break;

                    case EXIT:
                        running = false;

                        view.message("Goodbye. New records were stored "+ "in memory for this session only.");

                        break;
                }
            }

        } catch (NoSuchElementException ex) {

            view.message("\nInput ended. Application closed.");

        } finally {
            view.close();
        }
    }

    private void loadFile() {

        boolean loaded = false;

        //repeat until the user supplies a valid file
        while (!loaded) {

            String filename = view.read("Filename [Enter for Applicants_Form.txt]: ");

            if (filename.isEmpty()) {
                filename = "Applicants_Form.txt";
            }

            try {
                model.load(filename);

                loaded = true;

                view.message("File read successfully: "+ model.size()+ " employees.");

            } catch (IOException | IllegalArgumentException ex) {

                view.message("Unable to load file: " + ex.getMessage());
            }
        }
    }

    private void showSortedNames() {

        Employee[] sorted = model.getSortedEmployees();

        int limit = Math.min(20, sorted.length);

        view.message("First 20 names in alphabetical order:");

        for (int i = 0; i < limit; i++) {

            view.message((i + 1) + ". " + sorted[i].getName());
        }
    }

    private void searchEmployee() {

        String name = view.read("Full name to search: ");

        Employee[] matches = model.search(name);

        if (matches.length == 0) {

            view.message("Person not found.");

        } else {
            view.showRecords(matches);
        }
    }

    private void addRecord() {

        String name = view.read("Full name: ");

        //repeat the question when the name is invalid
        while (name.isEmpty() || name.contains(",")) {

            name = view.read("Enter a non-empty name without commas: ");
        }

        ManagerType manager = view.chooseManagerType();

        Department department = view.chooseDepartment();

        Employee employee = new Employee(name,manager,department);

        model.add(employee);

        view.message("All records added during this session:");

        view.showRecords(model.getAddedRecords());
    }

    private void showTree() {

        try {
            EmployeeTree tree = model.createTree();

            view.message("Employee binary tree in level order "+ "(structural positions):");

            String[] levels = tree.levelOrder();

            for(int i = 0; i < levels.length; i++) {
                view.message(levels[i]);
            }

            view.message("Height (levels): " + tree.height());

            view.message("Total nodes: " + tree.count());

        } catch (IllegalArgumentException ex) {

            view.message(ex.getMessage());
        }
    }
}
