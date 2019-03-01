//https://www.hackerrank.com/challenges/primsmstsub/problem
package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Special_Subtree {

    // Complete the prims function below.
	static int findParent(int node, int[] parent) {
		while(node != parent[node]) node = parent[node];
		return node;
	}
    static int prims(int n, int[][] edges, int start) {
    	int[] parent = new int[n];
    	for(int i = 0; i < n; i++) parent[i] = i;
    	
    	Arrays.sort(edges, new Comparator<int[]>() {			
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] > o2[2]) return 1;
				else if(o1[2] < o2[2]) return -1;
				else return 0;
			}
		});
    	int cnt_edges = 0;
    	int lenght_edges = 0;
    	for(int i = 0; i < edges.length; i++) {
    		int p1 = findParent(edges[i][0], parent);
    		int p2 = findParent(edges[i][1], parent);
    		if(p1 != p2) {
    			parent[p2] = p1;
    			lenght_edges += edges[i][2];
    			cnt_edges++;
    			if(cnt_edges == n - 1) break;
    		}
    	}
    	
    	return lenght_edges;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                if(j == 2) edges[i][j] = edgesItem;
                else edges[i][j] = edgesItem - 1;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);
        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
