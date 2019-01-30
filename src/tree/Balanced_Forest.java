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

public class Balanced_Forest {

    // Complete the balancedForest function below.
	
	static boolean[] visited;
	static long x;
	static long y;
	static int[] count = new int[2];
	static boolean found;
	static int numOfTree;
	
	
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
	
    static int balancedForest(int[] c, int[][] edges) {
        int[][] nodeValues = new int[c.length][2];
        long sum = 0, downB = 0, upB = 0;
        HashMap<Integer, TreeSet<Integer>> connectedEdges = new HashMap<Integer, TreeSet<Integer>>();
        
        for(int i = 0; i < edges.length; i++) {
        	int node1 = edges[i][0];
        	int node2 = edges[i][1];
        	if(connectedEdges.containsKey(node1)) connectedEdges.get(node1).add(node2);
        	else {
        		TreeSet<Integer> tmp = new TreeSet<Integer>();
        		connectedEdges.put(node1, tmp);
        		connectedEdges.get(node1).add(node2);
        	}
        	if(connectedEdges.containsKey(node2)) connectedEdges.get(node2).add(node1);
        	else {
        		TreeSet<Integer> tmp = new TreeSet<Integer>();
        		connectedEdges.put(node2, tmp);
        		connectedEdges.get(node2).add(node1);
        	}
        	
        }
        
        
    	for(int i = 0; i < c.length; i++) {
        	nodeValues[i][0] = i+1;
        	nodeValues[i][1] = c[i];
        	sum += c[i];
        }
    	sort(nodeValues, 1);
    	if((c.length + 1) % 3 != 0) {
    		return -1;
    	}
    	
    	numOfTree = (c.length + 1) / 3;
    	for(int i = 0; i < numOfTree; i++) {
    		downB += nodeValues[i][1];
    		upB += nodeValues[c.length - 1 - i][1];
    	}
    	for(long value = downB + 1; value < upB; value++) {
    		if(value * 3 <= sum) continue;
    		x = value;
    		y = sum - x * 2;
    		visited = new boolean[c.length + 1];
    			
			int root = 1;
    		int cntOfSub = 0;
    		while(visited[root] == true && root < c.length - 1) root++;
    		ArrayList<Integer> path = new ArrayList<Integer>();
    		long subSum = 0;
    		found = false;
    		dfs(cntOfSub, path, root, c, connectedEdges);
    		if(count[0] + count[1] == 0) continue;
    		
    		root = 1;
    		cntOfSub = 0;
    		while(visited[root] == true && root < c.length - 1) root++;
    		long subSum = 0;
    		found = false;
    		dfs(cntOfSub, path, root, c, connectedEdges);
    		if(count[0] + count[1] < 2) continue;
    		else {
    			return (int)(x - y);
    		}
    	}
    	
    	return -1;

    }
    
    private static void dfs(int cntOfSub, ArrayList<Integer> path, int root, int[] c, HashMap<Integer, TreeSet<Integer>> connectedEdges) {
    	if(found == true || visited[root] == true) return;
    	cntOfSub++;
    	subSum += c[root - 1];
    	
    	if(subSum == x && cntOfSub == numOfTree) {
    		count[0]++;
    		found = true;
    		return;
    	}
    	if(subSum == y && cntOfSub == numOfTree - 1) {
    		if(count[1] == 0) {
    			count[0]++;
    			found = true;
    			return;
    		}
    	}
    	if(connectedEdges.containsKey(root)) {
    		TreeSet<Integer> tmp = connectedEdges.get(root);
    		for(Integer node : tmp) {
    			if(visited[node] == false) dfs(cntOfSub, node, c, connectedEdges);
    		}
    	}
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

