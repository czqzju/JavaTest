package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */
	private static int findParent(int node, int[] parent) {
		int start = node;
		while(parent[start] != start) start = parent[start];
		return start;
	}

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
    	int n = gFrom.size();
    	int[][] edges = new int[n][3];
    	int[] parent = new int[gNodes + 1];
    	int numEdges = 0;
    	for(int i = 0; i < n; i ++) {
    		edges[i][0] =  gFrom.get(i);
    		edges[i][1] = gTo.get(i);
    		edges[i][2] = gWeight.get(i);
    	}
    	Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] > o2[2]) return 1;
				else if(o1[2] < o2[2]) return -1;
				else return 0;
			}
		});
    	
    	for(int i = 0; i < gNodes + 1; i++) parent[i] = i;
    	
    	int res = 0;
    	for(int i = 0; i < n; i++) {
    		int parent0 = findParent(edges[i][0], parent);
    		int parent1 = findParent(edges[i][1], parent);
    		if(parent0 != parent1) {
    			parent[parent1] = parent0;
    			res += edges[i][2];
    			numEdges++;
    			if(numEdges == gNodes - 1) break;
    		}
    	}
    	return res;
    	
    }

}

public class Really_Special_Subtree_Kruskal {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result.kruskals(gNodes, gFrom, gTo, gWeight);

        System.out.println(res);

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
