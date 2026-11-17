/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package departmentstore;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Gicely
 */
public class EmployeeTree {
        
    //each node contains one employee and up to two children
    private static class Node {
        Employee employee;
        Node left;
        Node right;

        Node(Employee employee) {
            this.employee = employee;
        }
    }

    private Node root;

    public void insert(Employee employee) {

        Node newNode = new Node(employee);

        //the first employee becomes the root
        if (root == null) {
            root = newNode;
            return;
        }

        //FIFO order allows breadth-first insertion
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            Node current = queue.remove();

            //fill the left child before the right child
            if (current.left == null) {
                current.left = newNode;
                return;
            }

            queue.add(current.left);

            if (current.right == null) {
                current.right = newNode;
                return;
            }

            queue.add(current.right);
        }
    }
}
