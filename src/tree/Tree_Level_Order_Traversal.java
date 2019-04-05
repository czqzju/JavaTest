package tree;
//https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
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

class Tree_Level_Order_Traversal {

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static void levelOrder(Node root) {
		Queue<Node> q = new ArrayDeque<>();
		if(root == null) return;
		q.add(root);
		while(!q.isEmpty()) {
			Node cur = (Node) q.remove();
			System.out.printf("%d ", cur.data);
			if(cur.left != null) q.add(cur.left);
			if(cur.right != null) q.add(cur.right);	
		}
      
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
        levelOrder(root);
    }	
}
