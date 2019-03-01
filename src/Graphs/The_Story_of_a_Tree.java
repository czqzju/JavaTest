//https://www.hackerrank.com/challenges/the-story-of-a-tree/problem
package Graphs;

import java.awt.TexturePaint;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

import animals.animal;

public class The_Story_of_a_Tree {

    /*
     * Complete the storyOfATree function below.
     */
	static int calcMaxCommonDividor(int a, int b) {
		int r;
		do {
			r = a % b;
			a = b;
			b = r;
		}while(r != 0);
		return a;
	}

	static void dfs_init(int root, int[] points, boolean[] visited, Map<Integer, Set<Integer>> relations, Map<Integer, Set<Integer>> parent) {
		visited[root] = true;
		if(relations.containsKey(root)) 
			for(int x : relations.get(root)) {
				if(!visited[x]) {
					if(parent.containsKey(x) && parent.get(x).contains(root)) {
						points[0] += 1;
					}
					dfs_init(x, points, visited, relations, parent);
				}
			}	
	}
	static void dfs_all(int root, int[] points, boolean[] visited, Map<Integer, Set<Integer>> relations, Map<Integer, Set<Integer>> parent) {
		if(relations.containsKey(root)) 
			for(int x : relations.get(root)) {
				if(!visited[x]) {
					points[x] = points[root];
					if(parent.containsKey(x) && parent.get(x).contains(root)) points[x] -= 1;
					if(parent.containsKey(root) && parent.get(root).contains(x)) points[x] +=1;
					visited[x] = true;
					dfs_all(x, points, visited, relations, parent);
				}
			}
	}
    static String storyOfATree(int n, int[][] edges, int k, int[][] guesses) {
        /*
         * Write your code here.
         */
    	int[] points = new int[n];
    	boolean[] visited = new boolean[n];
    	Map<Integer, Set<Integer>> relations = new HashMap<>();
    	Map<Integer, Set<Integer>> parent = new HashMap<>();
    	
    	for(int i = 0; i < edges.length; i++) {
    		int a = edges[i][0] - 1;
    		int b = edges[i][1] - 1;
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
    	
    	for(int i = 0; i < guesses.length; i++) {
    		int p = guesses[i][0] - 1;
    		int c = guesses[i][1] - 1;
    		if(parent.containsKey(c)) parent.get(c).add(p);
    		else {
    			Set<Integer> tmp = new HashSet<>();
    			parent.put(c, tmp);
    			parent.get(c).add(p);
    		}
    	}
    	dfs_init(0, points, visited, relations, parent);
    	Arrays.fill(visited, false);
    	visited[0] = true;
    	dfs_all(0, points, visited, relations, parent);
    	int cntWin = 0;
    	for(int i = 0; i < n; i++)
    		if(points[i] >= k)
    			cntWin++;
    	if(cntWin == 0) return String.valueOf(cntWin) + "/" + String.valueOf(1);
    	int maxCommonDiv = calcMaxCommonDividor(n, cntWin);
    	return String.valueOf(cntWin/maxCommonDiv) + "/" + String.valueOf(n/maxCommonDiv);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            int[][] edges = new int[n-1][2];

            for (int edgesRowItr = 0; edgesRowItr < n-1; edgesRowItr++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");

                for (int edgesColumnItr = 0; edgesColumnItr < 2; edgesColumnItr++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[edgesColumnItr].trim());
                    edges[edgesRowItr][edgesColumnItr] = edgesItem;
                }
            }

            String[] gk = scanner.nextLine().split(" ");

            int g = Integer.parseInt(gk[0].trim());

            int k = Integer.parseInt(gk[1].trim());

            int[][] guesses = new int[g][2];

            for (int guessesRowItr = 0; guessesRowItr < g; guessesRowItr++) {
                String[] guessesRowItems = scanner.nextLine().split(" ");

                for (int guessesColumnItr = 0; guessesColumnItr < 2; guessesColumnItr++) {
                    int guessesItem = Integer.parseInt(guessesRowItems[guessesColumnItr].trim());
                    guesses[guessesRowItr][guessesColumnItr] = guessesItem;
                }
            }

            String result = storyOfATree(n, edges, k, guesses);
            System.out.println(result);
//            bufferedWriter.write(result);
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();
    }
}
