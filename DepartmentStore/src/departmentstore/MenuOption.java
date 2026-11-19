/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

/**
 *
 * @author Gicely
 */
public enum MenuOption {
    
    //defines the main menu options
    SORT("Sort names"),
    SEARCH("Search by full name"),
    ADD_RECORDS("Add records"),
    CREATE_TREE("Create employee binary tree"),
    EXIT("Exit");

    private final String label;
    
    //store the text displayed for each option
    MenuOption(String label) {
        this.label = label;
    }
    
    //return the options display text
    @Override
    public String toString() {
        return label;
    }
    
}
