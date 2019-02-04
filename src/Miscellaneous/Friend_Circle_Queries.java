package Miscellaneous;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.xml.bind.ParseConversionEvent;

public class Friend_Circle_Queries {

    // Complete the maxCircle function below.
	static int findParent(int node, Map<Integer, int[]> trees) {
		if(!trees.containsKey(node)) {
			int[] tmp = {node, 1};
			trees.put(node, tmp);
			return node;
		}
		while(trees.get(node)[0] != node) node = trees.get(node)[0];
		return node;
	}
	static void updateTree(int parent, int child, Map<Integer, int[]> trees) {
		while(trees.get(child)[0] != parent) {
			int tmp = trees.get(child)[0];
			trees.get(child)[0] = parent;
			child = tmp;
		}
	}
    static int[] maxCircle(int[][] queries) {
    	int [] res = new int[queries.length];
    	Map<Integer, int[]> trees = new HashMap<>();
    	int resMax = 0;
    	
    	for(int i = 0; i < queries.length; i++) {
    		int parent = queries[i][0];
    		int child = queries[i][1];
    		
    		int parentP = findParent(parent, trees);
    		int parentC = findParent(child, trees);
    		if(parentP != parentC) {
    			updateTree(parentP, child, trees);
    			trees.get(parentP)[1] += trees.get(parentC)[1];
    			if(trees.get(parentP)[1] > resMax) resMax = trees.get(parentP)[1];
    		}
    		res[i] = resMax;
    	}
    	return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

//        for (int i = 0; i < ans.length; i++) {
////            bufferedWriter.write(String.valueOf(ans[i]));
//
//            if (i != ans.length - 1) {
//                bufferedWriter.write("\n");
//            }
//        }
//
////        bufferedWriter.newLine();
////
////        bufferedWriter.close();

        scanner.close();
    }
}

