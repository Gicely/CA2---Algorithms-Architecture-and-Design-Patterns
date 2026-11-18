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
    
    public String[] levelOrder() {
        
        String[] levels = new String[height()];
        
        if (root == null) {
            return levels;
        }
        
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            //count only the nodes belonging to the current level
            int nodesInLevel = queue.size();
            
            String text = "Level " + (level + 1) + ":\n";
            
            for (int i = 0; i < nodesInLevel; i++) {
                
                Node current = queue.remove();
                
                text += "  " + current.employee + "\n";
                
                if (current.left != null) {
                    queue.add(current.left);
                }
                
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            levels[level] = text;
            level++;
        }
        return levels;
    }
    
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        //an empty subtree has zero levels
        if (node == null) {
            return 0;
        }
        //add this level to the height of the deeper subtree
        return 1 + Math.max(height(node.left),height(node.right));
    }

    public int count() {
        return count(root);
    }

    private int count(Node node) {
        
        if (node == null) {
            return 0;
        }
        //count node and both subtrees
    return 1+ count(node.left)+ count(node.right);
    }
}
