//https://www.hackerrank.com/challenges/jack-goes-to-rapture/problem
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

class Result_1 {

    /*
     * Complete the 'getCost' function below.
     *
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
	static int res = -1;
	
	static void findPath(Set<Integer> path, int root, Map<Integer, HashMap<Integer, Integer>> relations, int cost, int end) {
		Set<Integer> tPath = path;//new HashSet<>();
		tPath.addAll(path);
		tPath.add(root);
		if(relations.containsKey(root)) {
			for(Map.Entry<Integer, Integer> tmp : relations.get(root).entrySet()) {
				if(tPath.contains(tmp.getKey())) continue;
				int tmpCost = tmp.getValue() - cost > 0? tmp.getValue() - cost : 0;
				tmpCost += cost;
				if(end == tmp.getKey()) {
					if(res == -1) res = tmpCost;
					else res = tmpCost < res? tmpCost : res;
					tPath.remove(root);
					return;
				}
				findPath(tPath, tmp.getKey(), relations, tmpCost, end);
			}
		}
		tPath.remove(root);
	}
    public static void getCost(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
    	Map<Integer, HashMap<Integer, Integer>> relations = new HashMap<>();
    	int end = gNodes - 1;
    	int start = 0;
    	for(int i = 0; i < gFrom.size(); i++) {
    		int v1 = gFrom.get(i) -1;
    		int v2 = gTo.get(i) - 1;
    		int weight = gWeight.get(i);
    		if(v1 == v2) continue;
    		if(relations.containsKey(v1)) {
    			if(relations.get(v1).containsKey(v2)) {
    				if(relations.get(v1).get(v2) > weight) relations.get(v1).put(v2, weight);
    			}
    			else relations.get(v1).put(v2, weight);
    		}
    		else {
    			HashMap<Integer, Integer> tmp = new HashMap<>();
    			relations.put(v1, tmp);
    			relations.get(v1).put(v2, weight);
    		}
    		if(relations.containsKey(v2)) {
    			if(relations.get(v2).containsKey(v1)) {
    				if(relations.get(v2).get(v1) > weight) relations.get(v2).put(v1, weight);
    			}
    			else relations.get(v2).put(v1, weight);
    		}
    		else {
    			HashMap<Integer, Integer> tmp = new HashMap<>();
    			relations.put(v2, tmp);
    			relations.get(v2).put(v1, weight);
    		}	
    	}
    	
    	Set<Integer> path = new HashSet<>();
    	int cost = 0;
    	findPath(path, start, relations, cost, end);
    	System.out.println(res);
    	

    }

}

public class Jack_goes_to_Rapture {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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

        Result_1.getCost(gNodes, gFrom, gTo, gWeight);

        bufferedReader.close();
    }
}
