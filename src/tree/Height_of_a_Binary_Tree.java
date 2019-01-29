package tree;

import java.util.*;
import java.util.concurrent.DelayQueue;
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

class Solution {

	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static int height(Node root) {
      	int res = -1;

      	Queue<Node> q = new ArrayDeque<Node>();
      	Queue<Node> q1 = new ArrayDeque<Node>();
      	
      	q.add(root);
      	
      	while(!q.isEmpty()) {
      		res++;
      		while(!q.isEmpty()) {
      			Node tmpNode = q.remove();
          		if(tmpNode.left != null) q1.add(tmpNode.left);
          		if(tmpNode.right != null) q1.add(tmpNode.right);
      		}
      		Queue<Node> tmp = q;
      		q = q1;
      		q1 = tmp;
      	}
      	
      	return res;
    }

	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
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
        int height = height(root);
        System.out.println(height);
    }	
}
