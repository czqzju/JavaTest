package tree;
//https://www.hackerrank.com/challenges/binary-search-tree-insertion/problem

import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Binary_Search_Tree_Insertion {
   
  	public static void preOrder( Node root ) {
      
    	if( root == null)
        	return;
      
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
     
    }

 /* Node is defined as :
 class Node 
    int data;
    Node left;
    Node right;
    
    */

	public static Node insert(Node root, int data) {
		if(root == null) return new Node(data);
        Node cur = root;
        Node parent = root;
        boolean isRight = false; 
        while(cur != null){
            if(data > cur.data){
                parent = cur;
                cur = cur.right;
                isRight = true;
            }
            else{
                parent = cur;
                cur = cur.left;
                isRight = false;
            }
        }
        if(isRight == true) parent.right = new Node(data);
        else parent.left = new Node(data);
        return root; 	
    }

	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
      	preOrder(root);
    }	
}
