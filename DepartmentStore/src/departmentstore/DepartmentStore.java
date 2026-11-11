/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package departmentstore;
/**
 *
 * @author Gicely
 */
public class DepartmentStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //test model
        Employee employee = new Employee (
                "Kate Summer",
                ManagerType.store_manager,
                Department.IT
        );
        
        System.out.println(employee);
    }
    
}
