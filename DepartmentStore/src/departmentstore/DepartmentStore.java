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
            
            Employee[] matches = model.search("Alex Turner");

            System.out.println("Search result:");

            for (int i = 0; i < matches.length; i++) {
                System.out.println(matches[i]);
            }

            Employee[] missing = model.search("Nobody Here");

            if (missing.length == 0) {
                System.out.println("Person not found.");
            }

        } catch (IOException | IllegalArgumentException ex) {

            System.out.println(
                    "Unable to run program: " + ex.getMessage());
        }
    }   
}
