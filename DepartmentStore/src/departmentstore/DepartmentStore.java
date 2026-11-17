/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package departmentstore;

import java.io.IOException;
/**
 *
 * @author Gicely
 */
public class DepartmentStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        StoreModel model = new StoreModel ();
        
        try {
            model.load("Applicants_Form.txt");

            //add one employee to test the registration rules
            Employee employee = new Employee(
                    "Aaron New",
                    ManagerType.team_lead,
                    Department.sales
            );

            model.add(employee);

            System.out.println("New records:");

            Employee[] added = model.getAddedRecords();

            for(int i = 0; i < added.length; i++) {
                System.out.println(added[i]);
            }

            System.out.println("Search after adding:");

            Employee[] matches = model.search("aaron new");

            for(int i = 0; i < matches.length; i++) {
                System.out.println(matches[i]);
            }

        }catch (IOException | IllegalArgumentException ex) {
            System.out.println("Unable to run program: " + ex.getMessage());
        }
    }
}