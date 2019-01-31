package tree;

import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


class treeNode{
	long sum;
	ArrayList<treeNode> children;
	int id;
	int data;
	treeNode parent;
	public treeNode(int id, int data) {
		this.id = id;
		this.data = data;
		sum = 0;
		children = new ArrayList<treeNode>();
		parent = null;
	}
}


public class Balanced_Forest {
	
	private static void sort(int[][] ob, final int order) {
		Arrays.sort(ob, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				int[] one = (int[]) o1;
				int[] two = (int[]) o2;
				
				int k = order;
				if(one[k] > two[k]) return 1;
				else if(one[k] < two[k]) return -1;
				else return 0;
				
			}
		});
	}   
	
	private static void createTree(treeNode root, HashMap<Integer, TreeSet<Integer>> connectedEdges, int[] c) {
		if(connectedEdges.containsKey(root.id)) {
			TreeSet<Integer> tmp= connectedEdges.get(root.id);
			for(Integer id : tmp) {
				if(root.parent != null && id != root.parent.id) {
					treeNode child = new treeNode(id, c[id - 1]);
					child.parent = root;
					root.children.add(child);
					createTree(child, connectedEdges, c);
					root.sum += child.sum;
				}
			}
			if(root.children.size() == 0) {
				root.sum = root.data;
			}
		}
	}
	
    static int balancedForest(int[] c, int[][] edges) {
        int[][] nodeValues = new int[c.length][2];
        long sum = 0, downB = 0, upB = 0;
        HashMap<Integer, TreeSet<Integer>> connectedEdges = new HashMap<Integer, TreeSet<Integer>>();
        int root_max = 0;
        int root_node = 0;
        for(int i = 0; i < edges.length; i++) {
        	int node1 = edges[i][0];
        	int node2 = edges[i][1];
        	if(connectedEdges.containsKey(node1)) connectedEdges.get(node1).add(node2);
        	else {
        		TreeSet<Integer> tmp = new TreeSet<Integer>();
        		connectedEdges.put(node1, tmp);
        		connectedEdges.get(node1).add(node2);
        	}
        	if(connectedEdges.get(node1).size() > root_max) {
        		root_max = connectedEdges.get(node1).size();
        		root_node = node1;
        	}
        	if(connectedEdges.containsKey(node2)) connectedEdges.get(node2).add(node1);
        	else {
        		TreeSet<Integer> tmp = new TreeSet<Integer>();
        		connectedEdges.put(node2, tmp);
        		connectedEdges.get(node2).add(node1);
        	}
        	if(connectedEdges.get(node2).size() > root_max) {
        		root_max = connectedEdges.get(node2).size();
        		root_node = node2;
        	}
        	
        }
        
        treeNode root = new treeNode(root_node, c[root_node - 1]);      
        treeNode[] treeNodes = new treeNode[c.length];
        treeNodes[root.id - 1] = root;
        

        createTree(root, connectedEdges, c);
        
        return -1;
    }
        

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int[][] edges = new int[n - 1][2];

            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int result = balancedForest(c, edges);
            System.out.println(result);

//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}

