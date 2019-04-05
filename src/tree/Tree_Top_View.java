package tree;

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

//https://www.hackerrank.com/challenges/tree-top-view/problem
class Tree_Top_View {

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
    static Map<Integer, int[]> m = new HashMap<>();

    private static void store_view(Node root, int i, int h){
        if(root == null) return;
        
        if(m.containsKey(i)) {
        	if(m.get(i)[0] > h) {
        		m.get(i)[0] = h;
        		m.get(i)[1] = root.data;
        	}
        }
        else {
        	int[] tmp = {h, root.data};
        	m.put(i, tmp);
        }
        
        if(root.left != null) store_view(root.left, i - 1, h + 1);
        if(root.right != null) store_view(root.right, i + 1, h + 1);
        
    }
    private static void print_top() {
    	int[] tmp = new int[m.size()];
    	int i = 0;
    	for(Integer key : m.keySet()) tmp[i++] = (int) key;
    	Arrays.sort(tmp);
    	for(i = 0; i < tmp.length; i++) System.out.printf("%d ", m.get(tmp[i])[1]);

    }
	public static void topView(Node root) {
        store_view(root, 0, 0);
        print_top();
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
        topView(root);
    }	
}