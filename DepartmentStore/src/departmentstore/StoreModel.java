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
    
    //store the records added during the current session
    private Employee[] addedRecords = new Employee[0];
    
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
        
        //reset the list of newly added records after loading
        addedRecords = new Employee[0];
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
    
    public Employee[] search(String name) {
        Employee[] sorted = getSortedEmployees();
        String target = name.trim();
        
        int found = EmployeeAlgorithms.binarySearch(sorted,0,sorted.length - 1,target);
        
        if (found == -1) {
            return new Employee[0];
        }
        
        //include all employees sharing same name
        int start = found;
        int end = found;
        
        while (start > 0 && sorted[start - 1].getName().equalsIgnoreCase(target)) {
            start--;
        }

        while (end < sorted.length - 1 && sorted[end + 1].getName().equalsIgnoreCase(target)) {
            end++;
        }
        
        Employee[] matches = new Employee[end - start + 1];
        
        for (int i = start; i <= end; i++) {
            matches[i - start] = sorted[i];
        }
        
        return matches;
    }
    //add the employee to both lists
    public void add(Employee employee) {
    employees = append(employees, employee);
    addedRecords = append(addedRecords, employee);
    }
    
    //return a copy of the session records
    public Employee[] getAddedRecords() {
        return addedRecords.clone();
    }
    
    //enforce minimum number of records
    public EmployeeTree createTree() {
        if (employees.length < 20) {
        throw new IllegalArgumentException("At least 20 employees are required.");
        }
        
        EmployeeTree tree = new EmployeeTree();

        //preserve the original file and insertion order
        for(int i = 0; i < employees.length; i++) {
            tree.insert(employees[i]);
        }
        return tree;
    }
}
