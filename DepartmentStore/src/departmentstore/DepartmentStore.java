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

            EmployeeTree tree = model.createTree();

            String[] levels = tree.levelOrder();

            for(int i = 0; i < levels.length; i++) {
                System.out.println(levels[i]);
            }

            System.out.println("Height (levels): " + tree.height());

            System.out.println("Total nodes: " + tree.count());
        
        } catch (IOException | IllegalArgumentException ex) {

            System.out.println("Unable to run program: " + ex.getMessage());
        }
    }
}