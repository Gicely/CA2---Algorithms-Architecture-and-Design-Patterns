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
            
            System.out.println("File read sucessfully: " + model.size() + " employees.");
            
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("Unable to load file: " + ex.getMessage());
        }
    }   
}
