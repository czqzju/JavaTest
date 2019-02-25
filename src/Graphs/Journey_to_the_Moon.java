package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Journey_to_the_Moon {

    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {
    	List<Set<Integer>> countries = new ArrayList<>();
    	int[] visited = new int[n];
    	Map<Integer, Set<Integer>> links = new HashMap<>();
    	
    	for(int i = 0; i < astronaut.length; i++) {
    		int a = astronaut[i][0];
    		int b = astronaut[i][1];
    		if(links.containsKey(a) == true) links.get(a).add(b);
    		else {
    			Set<Integer> tmp = new HashSet<>();
    			links.put(a, tmp);
    			links.get(a).add(b);
    		}
    		if(links.containsKey(b) == true) links.get(b).add(a);
    		else {
    			Set<Integer> tmp = new HashSet<>();
    			links.put(b, tmp);
    			links.get(b).add(a);
    		}
    	}
    	for(int i = 0; i < n; i++) {
    		if(visited[i] == 0) {
    			Set<Integer> country = new HashSet<>();
    			Queue<Integer> q = new ArrayDeque<>();
    			Queue<Integer> q1 = new ArrayDeque<>();
    			q.add(i);
    			while(!q.isEmpty()) {
    				while(!q.isEmpty()) {
	    				int cur = q.remove();
	    				country.add(cur);
	    				visited[cur] = 1;
	    				if(links.containsKey(cur) == true) {
	    					for(int k : links.get(cur)){
	    						if(visited[k] == 0) {
	    							q1.add(k);
	    						}
	    					}
	    				}
    				}
    				Queue<Integer> tmp = q;
    				q = q1;
    				q1 = tmp;
    			}
    			countries.add(country);
    		}
    	}
    	if(countries.size() <= 1) return 0;
    	
    	int total = n;
//    	for(int i = 0; i < countries.size(); i++) total += countries.get(i).size();
    	long res = 0;
    	for(int i = 0; i < countries.size(); i++) { 
    		total -= countries.get(i).size();
    		res += countries.get(i).size() * total;
    	}

    	return res;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
