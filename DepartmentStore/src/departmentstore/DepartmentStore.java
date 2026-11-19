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
        
        //create the model and the view
        StoreModel model = new StoreModel();
        StoreView view = new StoreView();

        //connect them through the controller.
        StoreController controller = new StoreController(model,view);

        //start the interactive application
        controller.run();
    }
}