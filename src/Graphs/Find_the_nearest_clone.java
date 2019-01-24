package Graphs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import javax.xml.ws.AsyncHandler;

public class Find_the_nearest_clone {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
	
	static int minPath;
	
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        minPath = 1000001;
        int count = 0;
        Set<Integer> rightColor = new HashSet<Integer>();
        for(int i = 0; i < ids.length; i++) {
        	if(ids[i] == val) {
        		rightColor.add(i + 1);
        		count++;
        	}
        }
        if(count < 2) {
        	return -1;
        }
        
        Map<Integer, List<Integer>> edges = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < graphFrom.length; i++) {
        	int from = graphFrom[i];
        	int to = graphTo[i];
        	if(edges.containsKey(from)) {
        		edges.get(from).add(to);
        	}
        	else {
        		edges.put(from, new ArrayList<Integer>());
        		edges.get(from).add(to);
        	}
        	if(edges.containsKey(to)) {
        		edges.get(to).add(from);
        	}
        	else {
        		edges.put(to, new ArrayList<Integer>());
        		edges.get(to).add(from);
        	}
        }
        
        for(Integer node : rightColor) {
        	bfs(graphNodes, node, edges, rightColor);
        }
        
        return minPath == 1000001? -1:minPath;

    }
    
    private static void bfs(int graphNodes, Integer val, Map<Integer, List<Integer>> edges, Set<Integer> rightColor) {
    	int [] visited = new int[graphNodes];
    	if(visited[val - 1] == 1) {
    		return;
    	}
    	visited[val - 1] = 1;
    	int distance = 1;
    	Boolean found = false;
    	Queue<Integer> q = new ArrayDeque<Integer>();
    	Queue<Integer> tmp = new ArrayDeque<Integer>();
    	if(!edges.containsKey(val) || edges.get(val).isEmpty()) {
    		return;
    	}
    	List<Integer> next = edges.get(val);
    	for(int i = 0; i < next.size(); i++) {
    		if(visited[next.get(i) - 1] == 0) q.add(next.get(i));
    	}
    	
    	while(!found && !q.isEmpty()) {
    		while(!q.isEmpty()) {
    			Integer nextNode = q.poll();
    			visited[nextNode - 1] = 1;
    			if(edges.containsKey(nextNode) && !edges.get(nextNode).isEmpty()) {
    				next = edges.get(nextNode);
    				for(int i = 0; i < next.size(); i++) {
    					if(visited[next.get(i) - 1] == 0) tmp.add(next.get(i));
    				}
    			}
    			if(rightColor.contains(nextNode)) {
    				if(distance < minPath) {
    					minPath = distance;
    					found = true;
    					break;
    				}
    			}
    		}
    		distance++;
    		q = tmp;
    	}
    	
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);
        System.out.println(ans);

//        bufferedWriter.write(String.valueOf(ans));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
