package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.SQLNonTransientConnectionException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Roads_and_Libraries {
	
	static int [] visited;
	static Map<Integer, List<Integer>> ads;

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
    	long res = (long)n * (long)c_lib;

    	
    	ads = new HashMap<Integer, List<Integer>>();
    	for(int i = 0; i < cities.length; i++) {
    		if(ads.containsKey(cities[i][0])) ads.get(cities[i][0]).add(cities[i][1]);
    		else {
    			List<Integer> tmp = new ArrayList<Integer>();
    			ads.put(cities[i][0], tmp);
    			ads.get(cities[i][0]).add(cities[i][1]);
    		}
    		if(ads.containsKey(cities[i][1])) ads.get(cities[i][1]).add(cities[i][0]);
    		else {
    			List<Integer> tmp = new ArrayList<Integer>();
    			ads.put(cities[i][1], tmp);
    			ads.get(cities[i][1]).add(cities[i][0]);
    		}
    	}
    	long numOfConnected = 0;
    	visited = new int[n+1];
    	
    	for(int i = 1; i <= n; i++) {
    		if(visited[i] == 0) {
    			dfs(i);
    			numOfConnected++;
    		}
    	}
    	
    	long cost = numOfConnected * c_lib + (n - 1 - (numOfConnected - 1)) * c_road;
    	return cost < res?cost:res;

    }
    private static void dfs(int i) {
    	if(visited[i] == 1 || !ads.containsKey(i)) {
    		return;
    	}
    	else {
    		visited[i] = 1;
    		List<Integer> adNodes = ads.get(i);
    		for(int j = 0; j < adNodes.size(); j++) {
    			dfs((int)adNodes.get(j));
    		}
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }


        scanner.close();
    }
}
