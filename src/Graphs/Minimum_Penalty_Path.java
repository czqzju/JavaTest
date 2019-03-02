//https://www.hackerrank.com/challenges/beautiful-path/problem
package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Minimum_Penalty_Path {

    // Complete the beautifulPath function below.
	
//	static void computeDis(int parent, int child, boolean[] visited, Map<Integer, HashMap<Integer, Integer>> relations, int[] dis) {
//		if(relations.containsKey(child)) {
//			for(Map.Entry<Integer, Integer> tmp : relations.get(child).entrySet()) {
//				int k = tmp.getKey();
//				if(k == parent) continue;
//				if(dis[k] == -1) dis[k] = dis[child] | tmp.getValue();
//				else{
//					int newDis = dis[child] | tmp.getValue();
//					dis[k] = newDis < dis[k]? newDis : dis[k];
//				}
//				if(!visited[k]) {
//					visited[k] = true;
//					computeDis(child, k, visited, relations, dis);
//				}
//			}
//		}
//	}
    static int beautifulPath(int n, int[][] edges, int A, int B) {
    	A -= 1;
    	B -= 1;
    	Map<Integer, HashMap<Integer, Integer>> relations = new HashMap<>();

    	Queue<Integer> q = new ArrayDeque<>();
    	Queue<Integer> q1 = new ArrayDeque<>();
    	
    	for(int i = 0; i < edges.length; i++) {
    		int v1 = edges[i][0] - 1;
    		int v2 = edges[i][1] - 1;
    		if(v1 == v2) continue;
    		if(relations.containsKey(v1)) {
    			if(relations.get(v1).containsKey(v2)) {
    				if(relations.get(v1).get(v2) > edges[i][2]) relations.get(v1).put(v2, edges[i][2]);
    			}
    			else relations.get(v1).put(v2, edges[i][2]);
    		}
    		else {
    			HashMap<Integer, Integer> tmp = new HashMap<>();
    			relations.put(v1, tmp);
    			relations.get(v1).put(v2, edges[i][2]);
    		}
    		if(relations.containsKey(v2)) {
    			if(relations.get(v2).containsKey(v1)) {
    				if(relations.get(v2).get(v1) > edges[i][2]) relations.get(v2).put(v1, edges[i][2]);
    			}
    			else relations.get(v2).put(v1, edges[i][2]);
    		}
    		else {
    			HashMap<Integer, Integer> tmp = new HashMap<>();
    			relations.put(v2, tmp);
    			relations.get(v2).put(v1, edges[i][2]);
    		}
    	}
    	boolean[][] were = new boolean[n][1024];
    	were[A][0] = true;
    	q.add(A);
    	q1.add(0);
    	
    	while(!q.isEmpty()) {

			int cur = q.remove();
			int k = q1.remove();

			if(relations.containsKey(cur)) {
				for(Map.Entry<Integer, Integer> tmp : relations.get(cur).entrySet()) {
					if(!were[tmp.getKey()][k | tmp.getValue()]) {
						were[tmp.getKey()][k | tmp.getValue()] = true;
						q.add(tmp.getKey());
						q1.add(k | tmp.getValue());
					}

				}
			}
    	}
    	for(int i = 0; i < 1024; i++) {
    		if(were[B][i]) return i;
    	}
    	
    	return -1;
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
                edges[i][j] = edgesItem;
            }
        }

        String[] AB = scanner.nextLine().split(" ");

        int A = Integer.parseInt(AB[0]);

        int B = Integer.parseInt(AB[1]);

        int result = beautifulPath(n, edges, A, B);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
