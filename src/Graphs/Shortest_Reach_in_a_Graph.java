package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.xml.ws.AsyncHandler;

public class Shortest_Reach_in_a_Graph {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
	
    static int [] findShortest(int graphNodes, int[] graphFrom, int[] graphTo, int val) {
        
        Map<Integer, Set<Integer>> edges = new HashMap<Integer, Set<Integer>>();
        for(int i = 0; i < graphFrom.length; i++) {
        	int from = graphFrom[i];
        	int to = graphTo[i];
        	if(edges.containsKey(from)) {
        		edges.get(from).add(to);
        	}
        	else {
        		edges.put(from, new HashSet<Integer>());
        		edges.get(from).add(to);
        	}
        	if(edges.containsKey(to)) {
        		edges.get(to).add(from);
        	}
        	else {
        		edges.put(to, new HashSet<Integer>());
        		edges.get(to).add(from);
        	}
        }
        

        return bfs(graphNodes, val, edges);
    }
    
    private static int [] bfs(int graphNodes, Integer val, Map<Integer, Set<Integer>> edges) {
    	int [] visited = new int[graphNodes];
    	int [] distance = new int[graphNodes];
    	int depth = 1;
    	for(int i = 0; i < graphNodes; i++) {
    		distance[i] = -1;
    	}
    	visited[val - 1] = 1;
    	Queue<Integer> q = new ArrayDeque<Integer>();
    	Queue<Integer> tmp = new ArrayDeque<Integer>();
    	
    	if(!edges.containsKey(val)) {
    		return distance;
    	}

    	Set<Integer> next = edges.get(val);
    	for(Integer node : next) {
    		if(visited[node - 1] == 0) {
    			visited[node - 1] = 1;
    			q.add(node);
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		while(!q.isEmpty()) {
    			Integer nextNode = q.poll();
    			distance[nextNode - 1] = 6 * depth;
    			if(edges.containsKey(nextNode) && !edges.get(nextNode).isEmpty()) {
    				next = edges.get(nextNode);
    				for(Integer node : next) {
    					if(visited[node - 1] == 0) {
    						visited[node - 1] = 1;
    						tmp.add(node);
    					}
    				}
    			}
    		}
    		depth++;
    		Queue<Integer> now = q;
    		q = tmp;
    		tmp = now;
    	}
    	return distance;
    	
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	
    	String qs = scanner.nextLine();
    	int q = Integer.parseInt(qs);
    	for(int n = 0; n < q; n++) {
	        String[] graphNodesEdges = scanner.nextLine().split(" ");
	        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
	        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());
	
	        int[] graphFrom = new int[graphEdges];
	        int[] graphTo = new int[graphEdges];
	
	        for (int i = 0; i < graphEdges; i++) {
	            String[] graphFromTo = scanner.nextLine().split(" ");
	            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
	            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
	        }
	
	//        long[] ids = new long[graphNodes];
	//
	//        String[] idsItems = scanner.nextLine().split(" ");
	//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
	//
	//        for (int i = 0; i < graphNodes; i++) {
	//            long idsItem = Long.parseLong(idsItems[i]);
	//            ids[i] = idsItem;
	//        }
	
	        int val = scanner.nextInt();
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
	
	        int [] ans = findShortest(graphNodes, graphFrom, graphTo, val);
//	        List<Integer> res = new ArrayList<Integer>();
	        for(int j = 0; j < graphNodes; j++) {
	        	if(j + 1 != val) System.out.printf("%d ", ans[j]);
	        }
	        
    	}

        scanner.close();
    }
}
