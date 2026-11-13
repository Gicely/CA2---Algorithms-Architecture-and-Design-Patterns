/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

/**
 *
 * @author Gicely
 */
public class EmployeeAlgorithms {
    
    //extra-array
    public static Employee[] quickSort(Employee[] arr) {

        //zero or one employee needs no sorting
        if (arr.length <= 1) {
            return arr;
        }

        Employee pivot = arr[arr.length - 1];

        int leftCount = 0;
        int rightCount = 0;

        //count the employees in each partition.
        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                leftCount++;

            } else {
                rightCount++;
            }
        }

        Employee[] left = new Employee[leftCount];
        Employee[] right = new Employee[rightCount];

        int l = 0;
        int r = 0;

        //fill up the left and right partitions
        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                left[l++] = arr[i];

            } else {
                right[r++] = arr[i];
            }
        }

        //sort the smaller arrays
        left = quickSort(left);
        right = quickSort(right);

        Employee[] result = new Employee[arr.length];
        int index = 0;

        //combine sorted left, pivot and sorted right.
        for (int i = 0; i < left.length; i++) {
            result[index++] = left[i];
        }
        result[index++] = pivot;

        for (int i = 0; i < right.length; i++) {
            result[index++] = right[i];
        }
        return result;
    }
    
}
