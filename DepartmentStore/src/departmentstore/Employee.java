/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

/**
 *
 * @author Gicely
 */
public class Employee {
    
    //store the employees informations.
    private final String name;
    private final ManagerType managerType;
    private final Department department;
            

    public Employee(
            String name,
            ManagerType managerType,
            Department department
    ) {
        //rejects invalid names
        if (name == null
                || name.trim().isEmpty()
                || name.contains(",")){
            
            throw new IllegalArgumentException ("Enter a name without commas.");
        }
        
        //every employee needs a role and a department
        if (managerType == null || department == null) {
            
            throw new IllegalArgumentException ("Role and department are required.");
        }
    
        this.name = name.trim();
        this.managerType = managerType;
        this.department = department;
    }
    
    //return the employees name
    public String getName(){
        return name;
    }
    
    //return the employees role
    public ManagerType getManagerType(){
        return managerType;
    }
    
    //return the employee department
    public Department getDepartment(){
        return department;
    }
    
    //defines how an employee appears when printed
    @Override
    public String toString(){
        return name + "|" + managerType + "|" + department;
    }
}
