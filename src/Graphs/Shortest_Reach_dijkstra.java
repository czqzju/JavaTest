package Graphs;
//https://www.hackerrank.com/challenges/bfsshortreach/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Shortest_Reach_dijkstra {

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
    	int[] res = new int[n - 1];
    	int[] visited = new int[n + 1];
    	int[] distance = new int[n + 1];
    	for(int i = 1; i < n + 1; i++) distance[i] = -1;
    	
    	Map<Integer, Set<Integer>> relations = new HashMap<>();
    	
    	for(int i = 0; i < m; i++) {
    		int a = edges[i][0];
    		int b = edges[i][1];
    		if(relations.containsKey(a) == true) relations.get(a).add(b);
    		else {
    			Set<Integer> tmp = new HashSet<>();
    			relations.put(a, tmp);
    			relations.get(a).add(b);
    		}
    		if(relations.containsKey(b) == true) relations.get(b).add(a);
    		else {
    			Set<Integer> tmp = new HashSet<>();
    			relations.put(b, tmp);
    			relations.get(b).add(a);
    		}
    	}
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	Queue<Integer> q1 = new ArrayDeque<>();
    	q.add(s);
    	int dis = 0;
    	visited[s] = 1;
		distance[s] = dis;
    	while(!q.isEmpty()) {
    		dis++;
    		while(!q.isEmpty()) {
	    		int cur = q.remove();
	    		if(relations.containsKey(cur)) {
	    			for(int k : relations.get(cur)) {
	    				if(visited[k] == 0) {
	    					q1.add(k);
	    					visited[k] = 1;
	    					distance[k] = dis;
	    				}
	    			}
	    		}
    		}
    		Queue<Integer> t = q;
    		q = q1;
    		q1 = t;
    		
    	}
    	for(int i = 0; i < s - 1; i ++) {
    		if(distance[i + 1] == -1) res[i] = -1;
    		else res[i] = distance[i + 1] * 6;
    	}
    	
    	for(int i = s - 1; i < n - 1; i++) {
    		if(distance[i + 2] == -1) res[i] = -1;
    		else res[i] = distance[i + 2] * 6;
    	}
    	
    	return res;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);
            for(int i = 0; i < result.length; i++) System.out.printf("%d ", result[i]);
            System.out.println();

//            for (int i = 0; i < result.length; i++) {
//                bufferedWriter.write(String.valueOf(result[i]));
//
//                if (i != result.length - 1) {
//                    bufferedWriter.write(" ");
//                }
//            }
//
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
