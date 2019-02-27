//https://www.hackerrank.com/challenges/the-quickest-way-up/problem
package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class The_Quickest_Way_Up {

    // Complete the quickestWayUp function below.
	static int findDestination(int cur, Map<Integer, Integer> m) {
		while(m.containsKey(cur) == true) cur = m.get(cur);
		return cur;
	}
    static int quickestWayUp(int[][] ladders, int[][] snakes) {
    	Map<Integer, Integer> m = new HashMap<>();
    	for(int i = 0; i < ladders.length; i++) m.put(ladders[i][0], ladders[i][1]);
    	for(int i = 0; i < snakes.length; i++) m.put(snakes[i][0], snakes[i][1]);
    	
    	int[] visited = new int[101];
    	Queue<Integer> q = new ArrayDeque<>();
    	Queue<Integer> q1 = new ArrayDeque<>();
    	q.add(1);
    	visited[1] = 1;
    	int cnt = 1;
    	while(!q.isEmpty()) {
    		while(!q.isEmpty()) {
    			int cur = q.remove();
    			for(int i = 1; i <= 6; i++) {
    				if(cur + i > 100) continue;
    				else if(cur + i == 100) return cnt;
    				else {
    					if(visited[cur + i] == 0) {
    						int des = findDestination(cur + i, m);
    						if(des == 100) return cnt;
    						if(visited[des] == 0) {
    							visited[des] = 1;
    							q1.add(des);
    						}
    					}
    				}
    			}
    		}
    		Queue<Integer> tmp = q;
    		q = q1;
    		q1 = tmp;
    		cnt++;
    	}
    	return -1;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] ladders = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] laddersRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int laddersItem = Integer.parseInt(laddersRowItems[j]);
                    ladders[i][j] = laddersItem;
                }
            }

            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] snakes = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] snakesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int snakesItem = Integer.parseInt(snakesRowItems[j]);
                    snakes[i][j] = snakesItem;
                }
            }

            int result = quickestWayUp(ladders, snakes);
            System.out.println(result);

//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
