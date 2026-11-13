/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Gicely
 */
public class StoreModel {
    
    //keep the employee order
    private Employee [] employees = new Employee [0];
    
    public void load (String filename) throws IOException{
        
        //validating the file
        Employee [] loaded = new Employee [0];
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(filename),
                StandardCharsets.UTF_8)){
            
            String header = reader.readLine();
                        
            //checking column format
            if (header == null || !header.replace ("\uFEFF", "").trim().equals("Name,ManagerType,Department")){
                throw new IllegalArgumentException ("Expected header: Name,ManagerType,Department");             
            }
            
            String line;
            int lineNumber = 1;
            
            while ((line = reader.readLine()) !=null) {
                
                lineNumber++;
                
                if (line.trim() .isEmpty()){
                    continue;
                }
                
                //incomplete records are rejected
                String [] fields = line.split (",", -1);
                
                try{
                    
                    if (fields.length !=3) {
                        throw new IllegalArgumentException ();
                    }
                    
                    Employee employee = new Employee (
                            fields [0].trim(), ManagerType.valueOf(fields [1].trim()), Department.valueOf (fields[2].trim()));
                    loaded = append (loaded, employee);
                    
                }catch (IllegalArgumentException ex){
                    
                    throw new IllegalArgumentException ("Invalid employee on line" + lineNumber);
                }
            }
        }
        if (loaded.length == 0) {
            throw new IllegalArgumentException ("The file contains no employee records.");
        }
        employees = loaded;
    }
    private Employee [] append (
            Employee [] original,
            Employee employee) {
        
        //array to fixe length
        Employee [] updated = new Employee [original.length +1];
        
        for (int i=0; i<original.length; i++){
            updated [i] = original [i];
        }
        updated [original.length] = employee;
        
        return updated;
    }
    public int size () {
        return employees.length;
    }
    //return a copy to protect the original array position
    public Employee [] getEmployees(){
        return employees.clone();
    }
    
    //sort a copy so the original insertion order is preserved
    public Employee[] getSortedEmployees() {
        return EmployeeAlgorithms.quickSort(getEmployees());
    }
}
