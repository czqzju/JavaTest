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
	static boolean checkRigth(int root, int n, Map<Integer, Set<Integer>> relations, int[][] guesses, int k) {
		int[] parent = new int[n + 1];
		parent[root] = -1;
		int cntRight = 0;
		Map<Integer, Integer> child_parent = new HashMap<>();
		for(int i =0; i < guesses.length; i++) child_parent.put(guesses[i][1], guesses[i][0]);
		
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> q1 = new ArrayDeque<>();
		q.add(root);
		while(!q.isEmpty()) {
			while(!q.isEmpty()) {
				int cur = q.remove();
				if(relations.containsKey(cur)) {
					for(int child : relations.get(cur)) {
						if(parent[child] == 0) {
							if(child_parent.containsKey(child) && child_parent.get(child) == cur) {
								cntRight++;
								if(cntRight >= k) return true;
								child_parent.remove(child);
								if(child_parent.isEmpty()) {
									if(cntRight >= k) return true;
									else return false;
								}
							}
							parent[child] = cur;
							q1.add(child);
						}
					}
				}
			}
			Queue<Integer> tmp = q;
			q = q1;
			q1 = tmp;
		}
		
		if(cntRight >= k) return true;
		else return false;
	}
    static String storyOfATree(int n, int[][] edges, int k, int[][] guesses) {
        /*
         * Write your code here.
         */
    	Map<Integer, Set<Integer>> relations = new HashMap<>();
    	for(int i = 0; i < edges.length; i++) {
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
    	int cntWin = 0;
    	for(int root = 1; root < n + 1; root++)
    		if(checkRigth(root, n, relations, guesses, k)) cntWin++;
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
